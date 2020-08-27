package com.itjiguang.yanxuan.seller.api;

import com.itjiguang.yanxuan.model.Account;

public interface IAccountService {
    /**
     * 根据登录的名称查询企业用户，
     * @param username
     * @return
     */
    Account queryByUserName(String username);
}
