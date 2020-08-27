package com.itjiguang.yanxuan.account.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itjiguang.yanxuan.account.api.IAreaService;
import com.itjiguang.yanxuan.mapper.SysAreaMapper;
import com.itjiguang.yanxuan.model.SysArea;
import com.itjiguang.yanxuan.model.SysAreaExample;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class AreaServiceImpl implements IAreaService {

    @Autowired
    private SysAreaMapper areaMapper;
    @Override
    public List<SysArea> getByCity(String cityId) {
        // 创建条件
        SysAreaExample sysAreaExample = new SysAreaExample();
        sysAreaExample.createCriteria().andCityIdEqualTo(cityId);
        // 查询
        List<SysArea> areaList = areaMapper.selectByExample(sysAreaExample);
        return areaList;
    }
}
