package com.itjiguang.yanxuan.goods.api;

import com.itjiguang.yanxuan.model.GoodsCategory;
import com.itjiguang.yanxuan.result.PageResult;
import com.itjiguang.yanxuan.viewmodel.Category;

public interface IGoodsCategoryService {
    /**
     * 根据条件进行分页查询
     * @param currentPage
     * @param pageSize
     * @param goodsCategory
     * @return
     */
    PageResult<GoodsCategory> pageQuery(Integer currentPage, Integer pageSize, GoodsCategory goodsCategory);

    /**
     * 完成数据的保存
     * @param category
     * @return
     */
    int save(Category category);

    /**
     * 根据主键进行此查询
     * @param id
     * @return
     */
    Category queryById(Long id);

    /**
     * 更新类目信息
     * @param category
     * @return
     */
    int update(Category category);
}
