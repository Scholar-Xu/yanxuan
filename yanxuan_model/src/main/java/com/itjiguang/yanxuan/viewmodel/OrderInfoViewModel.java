package com.itjiguang.yanxuan.viewmodel;

import com.itjiguang.yanxuan.model.OrderInfo;

import java.util.List;

public class OrderInfoViewModel extends OrderInfo {

    private Long addressId;// 收货地址的主键ID
    private List<CartInfo> cartInfoList; // 提交的所有商品

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public List<CartInfo> getCartInfoList() {
        return cartInfoList;
    }

    public void setCartInfoList(List<CartInfo> cartInfoList) {
        this.cartInfoList = cartInfoList;
    }
}
