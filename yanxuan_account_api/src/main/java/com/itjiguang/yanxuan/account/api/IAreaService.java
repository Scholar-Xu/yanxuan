package com.itjiguang.yanxuan.account.api;

import com.itjiguang.yanxuan.model.SysArea;

import java.util.List;

public interface IAreaService {

    List<SysArea> getByCity(String cityId);
}
