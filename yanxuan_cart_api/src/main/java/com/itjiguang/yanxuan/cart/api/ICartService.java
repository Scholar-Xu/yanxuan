package com.itjiguang.yanxuan.cart.api;

import com.itjiguang.yanxuan.model.OrderGoods;
import com.itjiguang.yanxuan.viewmodel.CartInfo;

import java.util.List;

public interface ICartService {

    /**
     * 把指定的商品添加到购物车中
     * @param cartInfoList
     * @param orderGoods
     * @return
     */
    List addCart(List<CartInfo> cartInfoList, OrderGoods orderGoods);

    /**
     * 把购物车的信息保存到Redis中
     * @param cartInfoList
     */
    void saveToRedis(String loginName, List<CartInfo> cartInfoList);

    /**
     * 从REdis中读取购物车的信息
     * @return
     */
    List<CartInfo> getFromRedis(String loginName);


    /**
     * 把cookie的购物车合并到redis的购物车
     * @param cookieCartList
     * @param redisCartList
     */
    List<CartInfo> mergeCartList(List<CartInfo> cookieCartList, List<CartInfo> redisCartList);
}
