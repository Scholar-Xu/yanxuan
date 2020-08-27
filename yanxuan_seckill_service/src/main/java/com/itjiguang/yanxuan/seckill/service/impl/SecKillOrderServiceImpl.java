package com.itjiguang.yanxuan.seckill.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itjiguang.yanxuan.mapper.AccountMapper;
import com.itjiguang.yanxuan.mapper.SecondKillGoodsMapper;
import com.itjiguang.yanxuan.model.Account;
import com.itjiguang.yanxuan.model.AccountExample;
import com.itjiguang.yanxuan.model.SecondKillGoods;
import com.itjiguang.yanxuan.model.SecondKillOrder;
import com.itjiguang.yanxuan.seckill.service.api.ISecKillOrderService;
import com.itjiguang.yanxuan.utils.SnowFlake;
import com.itjiguang.yanxuan.viewmodel.SecKillGoodsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SecKillOrderServiceImpl implements ISecKillOrderService {

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SecondKillGoodsMapper secondKillGoodsMapper;
    @Autowired
    private SnowFlake snowFlake;
    /**
     * 1. 读取秒杀商品的库存信息
     * 2. 校验秒杀商品的库存
     *      存在，减库存、并且生成预订单
     * @param id
     * @param loginName
     * @return
     */
    @Override
    public String createOrder(Long id, String loginName) {
        // 定义lua脚本
        /**
         *  1. 读取缓存中商品的库存信息
         *  2. 校验缓存
         *      大于0，减库存，返回true 1
         *      小于等于0 ，返回false 0
         */
        String script = "local stock = redis.call('hget', 'secKillGoods_stock', KEYS[1]);" +
                "if ( tonumber(stock) > 0 ) " +
                "then " +
                "redis.call('hincrby', 'secKillGoods_stock', KEYS[1], -1); " +
                "return 1; " +
                "else " +
                "return 0; " +
                "end ";

        DefaultRedisScript redisScript = new DefaultRedisScript(script, Boolean.class);

        List<String> keyList = new ArrayList<>();
        keyList.add(id.toString());

        Boolean flag = (Boolean)redisTemplate.execute(redisScript, keyList);

        if(flag){
            // 查询当前登录人的信息
            Account account = this.queryByLoginName(loginName);
            // 读取秒杀商品的信息
            SecKillGoodsInfo goodsInfo = (SecKillGoodsInfo)redisTemplate.boundHashOps("seckillGoods").get(id.toString());
            if(goodsInfo==null){
                throw new RuntimeException("商品不存在");
            }
            goodsInfo.setStockCount(goodsInfo.getStockCount()-1);
            // 更新mysql数据库
            SecondKillGoods secondKillGoods = new SecondKillGoods();
            secondKillGoods.setId(id);
            secondKillGoods.setStockCount(goodsInfo.getStockCount());

            secondKillGoodsMapper.updateByPrimaryKeySelective(secondKillGoods);


            // 生成预订单信息
            SecondKillOrder secondKillOrder = new SecondKillOrder();
            secondKillOrder.setId(snowFlake.nextId());
            secondKillOrder.setSeckillId(goodsInfo.getId());
            secondKillOrder.setSellerId(goodsInfo.getSellerId());
            secondKillOrder.setPayMoney(goodsInfo.getNewPrice());
            secondKillOrder.setAccountId(account.getId());
            secondKillOrder.setCreateDate(new Date());
            secondKillOrder.setStatus("0");

            redisTemplate.boundHashOps("secKillOrder_"+goodsInfo.getId()).put(account.getId().toString()+snowFlake.nextId(), secondKillOrder);

            return secondKillOrder.getId().toString();
        }else{
            throw new RuntimeException("库存不足");
        }

    }

    /**
     * 根据登录名称获取当前登录人的信息
     * @param loginName
     * @return
     */
    private Account queryByLoginName(String loginName){
        Account account = null;
        // 创建查询条件
        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andLoginNameEqualTo(loginName);
        // 进行查询
        List<Account> accountList = accountMapper.selectByExample(accountExample);
        if(accountList ==null){
            throw new RuntimeException("用户不存在，检查后再尝试");
        }else{
            account = accountList.get(0);
        }
        return account;
    }
}
