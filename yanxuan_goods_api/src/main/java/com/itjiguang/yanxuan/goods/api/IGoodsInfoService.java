package com.itjiguang.yanxuan.goods.api;

import com.itjiguang.yanxuan.model.GoodsSpu;
import com.itjiguang.yanxuan.result.PageResult;
import com.itjiguang.yanxuan.viewmodel.GoodsInfo;

public interface IGoodsInfoService {
    /**
     * 保存商品的信息
     * @param goodsInfo
     * @return
     */
    int save(GoodsInfo goodsInfo);

    /**
     * 根据条件查询店铺的商品列表
     * @param currentPage
     * @param pageSize
     * @param goodsSpu
     * @return
     */
    PageResult<GoodsSpu> pageQuery(Integer currentPage, Integer pageSize, GoodsSpu goodsSpu);

    /**
     * 根据主键查询商品的详细信息
     * @param id
     * @return
     */
    GoodsInfo queryById(Long id);

    /**
     * 修改商品信息
     * @param goodsInfo
     * @return
     */
    int update(GoodsInfo goodsInfo);

    /**
     * 完成商品的审核
     * @param goodsSpu
     * @return
     */
    int audit(GoodsSpu goodsSpu);
}
