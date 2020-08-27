package com.itjiguang.yanxuan.ad.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itjiguang.yanxuan.ad.api.IAdInfoService;
import com.itjiguang.yanxuan.mapper.AdInfoMapper;
import com.itjiguang.yanxuan.model.AdInfo;
import com.itjiguang.yanxuan.model.AdInfoExample;
import com.itjiguang.yanxuan.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdInfoServiceImpl implements IAdInfoService {

    @Autowired
    private AdInfoMapper adInfoMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public PageResult pageQuery(Integer currentPage, Integer pageSize, AdInfo adInfo) {
        if(adInfo!=null&& adInfo.getTypeId()!=null && !"".equals(adInfo.getTypeId())){
            PageResult pageResult = (PageResult)redisTemplate.boundHashOps("adInfo").get(adInfo.getTypeId().toString());
            if(pageResult!=null){
                System.out.println("从redis中获得数据");
                return pageResult;
            }
        }
        // 构建查询条件
        AdInfoExample adInfoExample = new AdInfoExample();
        AdInfoExample.Criteria criteria = adInfoExample.createCriteria();
        if(adInfo!=null){
            if(adInfo.getName()!=null && !"".equals(adInfo.getName())){
                criteria.andNameLike("%"+adInfo.getName()+"%");
            }

            // 广告类型
            if(adInfo.getTypeId()!=null && !"".equals(adInfo.getTypeId())){
                criteria.andTypeIdEqualTo(adInfo.getTypeId());
            }
            // 查询状态
            if(adInfo.getStatus()!=null && !"".equals(adInfo.getStatus())){
                criteria.andStatusEqualTo(adInfo.getStatus());
            }
        }
        // 开启分页查询
        PageHelper.startPage(currentPage,pageSize);
        // 查询
        Page<AdInfo> pageData = (Page<AdInfo>)adInfoMapper.selectByExampleWithBLOBs(adInfoExample);
        // 构建返回结果
        PageResult<AdInfo> pageResult = new PageResult<>();
        pageResult.setResult(pageData.getResult());
        pageResult.setTotal(pageData.getTotal());

        // 把数据保存到Redis
        if(adInfo!=null&& adInfo.getTypeId()!=null && !"".equals(adInfo.getTypeId())){
            redisTemplate.boundHashOps("adInfo").put(adInfo.getTypeId().toString(), pageResult);
        }

        return pageResult;
    }

    @Override
    public int save(AdInfo adInfo) {
        // 清空缓存
        redisTemplate.boundHashOps("adInfo").delete(adInfo.getTypeId().toString());
        return adInfoMapper.insert(adInfo);
    }

    @Override
    public int update(AdInfo adInfo) {
        return adInfoMapper.updateByPrimaryKeyWithBLOBs(adInfo);
    }

    @Override
    public int deleteById(Long id) {
        AdInfo adInfo1 = adInfoMapper.selectByPrimaryKey(id);
        redisTemplate.boundHashOps("adInfo").delete(adInfo1.getTypeId().toString());

        AdInfo adInfo = new AdInfo();
        adInfo.setId(id);
        adInfo.setStatus("1");
        return adInfoMapper.updateByPrimaryKeySelective(adInfo);
    }

    @Override
    public AdInfo queryById(Long id) {
        return adInfoMapper.selectByPrimaryKey(id);
    }
}
