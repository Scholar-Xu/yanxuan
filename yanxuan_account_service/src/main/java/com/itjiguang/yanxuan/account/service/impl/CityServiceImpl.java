package com.itjiguang.yanxuan.account.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itjiguang.yanxuan.account.api.ICityService;
import com.itjiguang.yanxuan.mapper.SysCityMapper;
import com.itjiguang.yanxuan.model.SysCity;
import com.itjiguang.yanxuan.model.SysCityExample;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class CityServiceImpl implements ICityService {

    @Autowired
    private SysCityMapper cityMapper;

    @Override
    public List<SysCity> getByProvince(String provinceId) {
        // 创建条件
        SysCityExample sysCityExample = new SysCityExample();
        sysCityExample.createCriteria().andProvinceIdEqualTo(provinceId);
        // 进行查询
        List<SysCity> cityList = cityMapper.selectByExample(sysCityExample);
        return cityList;
    }
}
