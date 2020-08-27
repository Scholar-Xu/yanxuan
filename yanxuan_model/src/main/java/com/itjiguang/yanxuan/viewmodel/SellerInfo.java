package com.itjiguang.yanxuan.viewmodel;

import com.itjiguang.yanxuan.model.SellerShop;

/**
 * 封装商家入驻的信息
 */
public class SellerInfo extends SellerShop {

    private String loginName;
    private String password;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
