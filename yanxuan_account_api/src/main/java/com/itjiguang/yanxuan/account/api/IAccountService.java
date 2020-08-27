package com.itjiguang.yanxuan.account.api;

import com.itjiguang.yanxuan.model.Account;

public interface IAccountService {

    /**
     * 保存用户的注册信息
     * @param account
     * @return
     */
    public String registAccount(Account account);
}
