package com.itjiguang.yanxuan.account.api;

import com.itjiguang.yanxuan.model.SysCity;

import java.util.List;

public interface ICityService {

    List<SysCity> getByProvince(String provinceId);
}
