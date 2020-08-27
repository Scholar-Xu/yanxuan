package com.itjiguang.yanxuan.account.api;

import com.itjiguang.yanxuan.model.AccountAddress;

import java.util.List;

public interface IAddressService {

    int save(String loginName, AccountAddress accountAddress);


    List<AccountAddress> get(String loginName);
}
