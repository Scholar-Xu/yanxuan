package com.itjiguang.yanxuan.viewmodel;

import com.itjiguang.yanxuan.model.OrderGoods;

import java.io.Serializable;
import java.util.List;

public class CartInfo implements Serializable {

    private Long sellerId;// 店铺的主键ID
    private String sellerName;// 店铺的名称
    private List<OrderGoods> orderGoodsList;// 购物车中的商品的信息

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public List<OrderGoods> getOrderGoodsList() {
        return orderGoodsList;
    }

    public void setOrderGoodsList(List<OrderGoods> orderGoodsList) {
        this.orderGoodsList = orderGoodsList;
    }
}
