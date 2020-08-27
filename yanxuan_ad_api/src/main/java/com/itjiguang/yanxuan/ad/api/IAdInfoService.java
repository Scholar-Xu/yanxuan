package com.itjiguang.yanxuan.ad.api;

import com.itjiguang.yanxuan.model.AdInfo;
import com.itjiguang.yanxuan.result.PageResult;

public interface IAdInfoService {
    /**
     * 根据条件进行分页查询
     * @param currentPage
     * @param pageSize
     * @param adInfo
     * @return
     */
    PageResult pageQuery(Integer currentPage, Integer pageSize, AdInfo adInfo);

    /**
     * 保存广告信息
     * @param adInfo
     * @return
     */
    int save(AdInfo adInfo);

    /**
     * 修改广告的信息
     * @param adInfo
     * @return
     */
    int update(AdInfo adInfo);

    /**
     * 删除广告的信息
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    AdInfo queryById(Long id);
}
