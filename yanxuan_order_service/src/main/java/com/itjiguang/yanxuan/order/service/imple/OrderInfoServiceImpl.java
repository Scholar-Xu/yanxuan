package com.itjiguang.yanxuan.order.service.imple;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.itjiguang.yanxuan.mapper.*;
import com.itjiguang.yanxuan.model.*;
import com.itjiguang.yanxuan.order.IOrderInfoService;
import com.itjiguang.yanxuan.utils.SnowFlake;
import com.itjiguang.yanxuan.viewmodel.CartInfo;
import com.itjiguang.yanxuan.viewmodel.OrderInfoViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class OrderInfoServiceImpl implements IOrderInfoService {

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private SnowFlake snowFlake;
    @Autowired
    private AccountAddressMapper addressMapper;
    @Autowired
    private SysProvinceMapper provinceMapper;
    @Autowired
    private SysCityMapper cityMapper;
    @Autowired
    private SysAreaMapper areaMapper;
    @Autowired
    private OrderGoodsMapper orderGoodsMapper;
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private PayLogMapper payLogMapper;

    /**
     *  针对每一个商家生成一条订单信息， 遍历传递参数的商品列表，拿到每一个商家的编号
     *  创建OrderInfo的对象，设置详细信息
     *      1. 设置已有的信息  accountId、sellerId、传递过来的支付方式、运费、留言、状态
     *      2. 订单编号：应该是随机的字符
     *      3. 收货地址：根据addressId进行查询，并且进行设置
     *      4. 商品的总价、订单的金额（总价+运费）
     *      5. 保存订单关联的商品的信息
     *  后续操作：
     *  1. 从购物车中移除已经在订单中存在的商品
     * @param loginName
     * @param orderInfoViewModel
     * @return
     */
    @Override
    public String saveOrderInfo(String loginName, OrderInfoViewModel orderInfoViewModel) {
        // 创建查询条件
        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andLoginNameEqualTo(loginName);
        List<Account> accountList = accountMapper.selectByExample(accountExample);
        if(accountList.size()<0){
            throw new RuntimeException("账户信息不存在，请检查");
        }
        Account account = accountList.get(0);
        // 查询收货人的信息
        Long addressId = orderInfoViewModel.getAddressId();
        AccountAddress address = addressMapper.selectByPrimaryKey(addressId);
        // 省市区信息的查询
        SysProvince province = provinceMapper.selectByPrimaryKey(address.getAreaProvinceId());
        SysCity city = cityMapper.selectByPrimaryKey(address.getAreaCityId());
        SysArea area = areaMapper.selectByPrimaryKey(address.getAreaTownId());
        String addressStr = province.getName() + city.getName()+ area.getName() + address.getDetailAddress();

        // 购物车中商品信息的查询
        List<CartInfo> cartListRedis = (List<CartInfo>)redisTemplate.boundHashOps("cartList").get(loginName);
        List<Long> redisSkuIdList = new ArrayList<>();

        // 存储商品订单编号
        List goodsOrderNoList = new ArrayList<Long>();
        // 计算支付的金额
        double payMoney = 0;
//        int result = 0;
        // 创建订单信息并且保存
        List<CartInfo> cartInfoList = orderInfoViewModel.getCartInfoList();
        for (CartInfo cartInfo : cartInfoList) {
            // 创建订单
            OrderInfo orderInfo = new OrderInfo();
            // 设置已有的值
            orderInfo.setAccountId(account.getId());
            orderInfo.setSellerId(cartInfo.getSellerId());
            orderInfo.setPayType(orderInfoViewModel.getPayType());
            orderInfo.setFareFee(orderInfoViewModel.getFareFee());
            orderInfo.setMessage(orderInfoViewModel.getMessage());
            orderInfo.setStatus(orderInfoViewModel.getStatus());
            // 设置创建时间
            orderInfo.setCreateDate(new Date());
            // 获取订单编号
            long orderNum = snowFlake.nextId();
            orderInfo.setOrderNo(orderNum);
            // 设置地址信息
            orderInfo.setReceiver(address.getReceiver());
            orderInfo.setPhoneNum(address.getPhoneNum());
            orderInfo.setDetailAddress(addressStr);

            double goodsMoney = 0;

            // 遍历当前CartInfo中的商品列表
            for (OrderGoods orderGoods : cartInfo.getOrderGoodsList()) {
                // 计算订单的总价值
                goodsMoney += orderGoods.getTotalFee().doubleValue();
                // 保存订单关联的商品
                orderGoods.setOrderId(orderInfo.getOrderNo());
                orderGoods.setSellerId(cartInfo.getSellerId());

                orderGoodsMapper.insert(orderGoods);

                // 临时保存
                redisSkuIdList.add(orderGoods.getSkuId());
            }

            // 设置当前订单的总价格
            orderInfo.setGoodsFee(new BigDecimal(goodsMoney));
            orderInfo.setTotalFee(new BigDecimal(goodsMoney));

            // 保存订单信息
            int insert = orderInfoMapper.insert(orderInfo);
            // 计算支付金额
            payMoney += orderInfo.getTotalFee().doubleValue();
            // 保存订单编号
            goodsOrderNoList.add(orderInfo.getId());
//            result += insert;
        }

        // 遍历购物车中的商品信息，进行移除
        Iterator<CartInfo> cartIntoIterator = cartListRedis.iterator();
        while (cartIntoIterator.hasNext()){
            List<OrderGoods> orderGoodsList = cartIntoIterator.next().getOrderGoodsList();
            Iterator<OrderGoods> iterator = orderGoodsList.iterator();
            while(iterator.hasNext()){
                OrderGoods orderGoods = iterator.next();
                if(redisSkuIdList.contains(orderGoods.getSkuId())){
                    iterator.remove();
                }
            }

            if(orderGoodsList.size()<0){
                cartIntoIterator.remove();
            }
        }

        // 保存到redis中
        redisTemplate.boundHashOps("cartList").put(loginName, cartListRedis);

        // 生成订单支付的信息
        PayLog payLog = new PayLog();
        payLog.setAccountId(account.getId());
        payLog.setPayOrderNo(snowFlake.nextId()+"");
        payLog.setCreateDate(new Date());
        payLog.setTradeStatus("0");
        payLog.setPayType("0");
        // 设置支付金额
        payLog.setTotalFee((long)(payMoney*100));
        // 设置关联的商品订单编号
        payLog.setGoodsOrderNo(JSON.toJSONString(goodsOrderNoList));

        payLogMapper.insertSelective(payLog);

        return payLog.getPayOrderNo();
    }
}
