package com.itjiguang.yanxuan.cart.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.itjiguang.yanxuan.cart.api.ICartService;
import com.itjiguang.yanxuan.mapper.GoodsSkuMapper;
import com.itjiguang.yanxuan.model.GoodsSku;
import com.itjiguang.yanxuan.model.OrderGoods;
import com.itjiguang.yanxuan.viewmodel.CartInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    private GoodsSkuMapper goodsSkuMapper;
    /**
     * 把指定的orderGoods添加到CartInfoList中
     * 1. cartInfoList本身就是空的，肯定是没有CartInfo，根据OrderGoods创建CartInfo，并且把CartInfo添加到CartInfoList中
     * 2. cartInfoList不是空的，但是没有CartInfo，根据OrderGoods创建CartInfo，，并且把CartInfo添加到CartInfoList中
     * 3. cartInfoList不是空的，里面有对应的CartInfo，只需要把当前OrderGoods添加到CartInfo中的OrderGoodsList
     *      3.1. OrderGoodsList中是否存在当前的orderGoods，如果存在修改数量
     *      3.2. OrderGoodsList中是否存在当前的orderGoods，如果不存在那么就需要把orderGoods添加到OrderGoodsList中
     *
     *  逻辑判断
     *  1. CartInfoList中是否存在与当前OrderGoods对应的SellerName一致的CartInfo
     *  2. OrderGoodsList中是否存在与当前OrderGoods对应的OrderGoods， orderGoods.skuId
     * @param cartInfoList
     * @param orderGoods
     * @return
     */
    @Override
    public List addCart(List<CartInfo> cartInfoList, OrderGoods orderGoods) {
        // 查询 根据skuId查询详细信息
        GoodsSku goodsSku = goodsSkuMapper.selectByPrimaryKey(orderGoods.getSkuId());

        // 从购物车中读取CartInfo
        CartInfo targetCartInfo = this.getCartInfo(cartInfoList, goodsSku.getSellerId());
        if(targetCartInfo==null){
            // 创建一个CartInfo存放到CartInfoList中
            targetCartInfo = new CartInfo();
            // 设置商铺的信息
            targetCartInfo.setSellerId(goodsSku.getSellerId());
            targetCartInfo.setSellerName(goodsSku.getSellerName());
            // 创建OrderGoodsList
            List<OrderGoods> orderGoodsList = new ArrayList<>();

            // 完善OrderGoods
            this.createOrderGoods(orderGoods, goodsSku);
            orderGoodsList.add(orderGoods);
            targetCartInfo.setOrderGoodsList(orderGoodsList);

            // 把CartInfo添加到CartInfoList
            cartInfoList.add(targetCartInfo);
        }else{
            // 进行查找OrderGoods
            OrderGoods targetOrderGoods = this.getOrderGoods(targetCartInfo.getOrderGoodsList(), goodsSku.getId());
            if(targetOrderGoods==null){
                // 完善OrderGoods添加OrderGoodsList中
                this.createOrderGoods(orderGoods, goodsSku);

                targetCartInfo.getOrderGoodsList().add(orderGoods);
            }else{
                // 修改targetOrderGoods的数量、总计
                // 计算数量
                Integer targetCount = targetOrderGoods.getCount() + orderGoods.getCount();
                if(targetCount<=0){
                    // 从OrderGoodsList中移除当前的orderGoods
                    targetCartInfo.getOrderGoodsList().remove(targetOrderGoods);
                    if(targetCartInfo.getOrderGoodsList().size()<=0){
                        cartInfoList.remove(targetCartInfo);
                    }
                }else{
                    targetOrderGoods.setCount(targetCount);
                    // 设置小计
                    targetOrderGoods.setTotalFee(new BigDecimal(targetOrderGoods.getGoodsPrice().doubleValue()*targetCount));
                }
            }
        }

        return cartInfoList;
    }

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void saveToRedis(String loginName, List<CartInfo> cartInfoList) {
        redisTemplate.boundHashOps("cartList").put(loginName, cartInfoList);
    }

    @Override
    public List<CartInfo> getFromRedis(String loginName) {
        return (List<CartInfo>)redisTemplate.boundHashOps("cartList").get(loginName);
    }

    @Override
    public List<CartInfo> mergeCartList(List<CartInfo> cookieCartList, List<CartInfo> redisCartList) {
        for (CartInfo cartInfo : cookieCartList) {
            for (OrderGoods orderGoods: cartInfo.getOrderGoodsList() ) {
                this.addCart(redisCartList, orderGoods);
            }
        }

        return redisCartList;
    }

    /**
     * 从购物车中找到指定店铺的cartInfo信息
     * @param cartInfoList
     * @param sellerId
     * @return
     */
    private CartInfo getCartInfo(List<CartInfo> cartInfoList, Long sellerId){
        for (CartInfo cartInfo : cartInfoList) {
            if(cartInfo.getSellerId() == sellerId){
                return cartInfo;
            }
        }
        return null;
    }

    /**
     * 从购物车中找到指定skuId的orderGoods
     * @param orderGoodsList
     * @param skuId
     * @return
     */
    private OrderGoods getOrderGoods(List<OrderGoods> orderGoodsList, Long skuId){
        for (OrderGoods orderGoods : orderGoodsList) {
            if(orderGoods.getSkuId() == skuId){
                return orderGoods;
            }
        }
        return null;
    }

    /**
     * 完善orderGoods
     * @param orderGoods
     * @param goodsSku
     */
    private void createOrderGoods(OrderGoods orderGoods, GoodsSku goodsSku){
        // 完善orderGoods
        orderGoods.setGoodsId(goodsSku.getGoodsId());
        orderGoods.setGoodsPrice(goodsSku.getPrice());
        orderGoods.setGoodsLabel(goodsSku.getLabel());
        // 图片
        List<String> picUrlList = JSON.parseArray(goodsSku.getPicUrl(), String.class);
        orderGoods.setPicUrl(picUrlList.get(0));
        // 设置小计
        orderGoods.setTotalFee(new BigDecimal(orderGoods.getGoodsPrice().doubleValue()*orderGoods.getCount()));
    }
}
