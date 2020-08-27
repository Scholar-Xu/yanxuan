package com.itjiguang.yanxuan.goods.api;

import com.itjiguang.yanxuan.model.GoodsSpec;
import com.itjiguang.yanxuan.result.PageResult;
import com.itjiguang.yanxuan.viewmodel.Specification;

public interface IGoodsSpecService {
    /**
     * 根据条件进行分页查询
     * @param currentPage
     * @param pageSize
     * @param goodsSpec
     * @return
     */
    PageResult<GoodsSpec> pageQuery(Integer currentPage, Integer pageSize, GoodsSpec goodsSpec);

    /**
     * 保存规格信息、规格项信息
     * @param specification
     * @return
     */
    int save(Specification specification);

    /**
     * 根据主键进行删除
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    Specification queryById(Long id);

    /**
     * 根据主键进行更新
     * @param specification
     * @return
     */
    int update(Specification specification);
}
