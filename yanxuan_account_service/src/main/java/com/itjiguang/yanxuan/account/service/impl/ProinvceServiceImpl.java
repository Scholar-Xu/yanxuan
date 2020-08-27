package com.itjiguang.yanxuan.account.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itjiguang.yanxuan.account.api.IProvinceService;
import com.itjiguang.yanxuan.mapper.SysProvinceMapper;
import com.itjiguang.yanxuan.model.SysProvince;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ProinvceServiceImpl implements IProvinceService {

    @Autowired
    private SysProvinceMapper provinceMapper;
    @Override
    public List<SysProvince> get() {
        return provinceMapper.selectByExample(null);
    }
}
