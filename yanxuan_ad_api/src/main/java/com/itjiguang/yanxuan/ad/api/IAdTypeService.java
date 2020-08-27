package com.itjiguang.yanxuan.ad.api;

import com.itjiguang.yanxuan.model.AdType;
import com.itjiguang.yanxuan.result.PageResult;

public interface IAdTypeService {
    /**
     * 根据条件进行分页查询
     * @param currentPage
     * @param pageSize
     * @param adType
     * @return
     */
    PageResult<AdType> pageQuery(Integer currentPage, Integer pageSize, AdType adType);

    /**
     * 保存广告的类型
     * @param adType
     * @return
     */
    int save(AdType adType);

    /**
     * 更新类型信息
     * @param adType
     * @return
     */
    int update(AdType adType);

    /**
     * 根据主键进行删除
     * @param id
     * @return
     */
    int deleteById(Long id);
}
