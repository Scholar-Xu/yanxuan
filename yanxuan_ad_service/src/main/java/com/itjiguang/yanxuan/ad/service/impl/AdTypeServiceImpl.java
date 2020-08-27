package com.itjiguang.yanxuan.ad.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itjiguang.yanxuan.ad.api.IAdTypeService;
import com.itjiguang.yanxuan.mapper.AdTypeMapper;
import com.itjiguang.yanxuan.model.AdType;
import com.itjiguang.yanxuan.model.AdTypeExample;
import com.itjiguang.yanxuan.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdTypeServiceImpl implements IAdTypeService {

    @Autowired
    private AdTypeMapper adTypeMapper;

    @Override
    public PageResult<AdType> pageQuery(Integer currentPage, Integer pageSize, AdType adType) {
        // 构建查询条件
        AdTypeExample adTypeExample = new AdTypeExample();
        if(adType!=null){
            if(adType.getName()!=null && !"".equals(adType.getName())){
                adTypeExample.createCriteria().andNameLike("%"+adType.getName()+"%");
            }
        }
        // 开启分页
        PageHelper.startPage(currentPage, pageSize);
        // 查询
        Page<AdType> pageData = (Page<AdType>)adTypeMapper.selectByExample(adTypeExample);
        // 构建返回结果
        PageResult<AdType> pageResult = new PageResult<>();
        pageResult.setResult(pageData.getResult());
        pageResult.setTotal(pageData.getTotal());
        return pageResult;
    }

    @Override
    public int save(AdType adType) {
        int insert = adTypeMapper.insert(adType);
        return insert;
    }

    @Override
    public int update(AdType adType) {
        return adTypeMapper.updateByPrimaryKey(adType);
    }

    @Override
    public int deleteById(Long id) {
        return adTypeMapper.deleteByPrimaryKey(id);
    }
}
