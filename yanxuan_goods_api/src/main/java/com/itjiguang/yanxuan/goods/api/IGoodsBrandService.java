package com.itjiguang.yanxuan.goods.api;

import com.itjiguang.yanxuan.model.GoodsBrand;
import com.itjiguang.yanxuan.result.PageResult;

import java.util.List;

/**
 * 商品列表的服务接口
 */
public interface IGoodsBrandService {

    /**
     * 查询返回所有的商品品牌信息
     * @return
     */
    public List<GoodsBrand> queryAll();

    /**
     * 根据分页参数进行分页数据的查询
     * @param currentPage
     * @param pageSize
     * @return
     */
    public PageResult<GoodsBrand> pageQuery(Integer currentPage, Integer pageSize);

    /**
     * 根据查询条件、分页参数进行条件分页查询
     * @param currentPage
     * @param pageSize
     * @param goodsBrand
     * @return
     */
    public PageResult<GoodsBrand> pageQuery(Integer currentPage, Integer pageSize, GoodsBrand goodsBrand);

    /**
     * 新增数据保存
     * @param goodsBrand
     */
    int save(GoodsBrand goodsBrand);

    /**
     * 修改数据保存
     * @param goodsBrand
     */
    int update(GoodsBrand goodsBrand);

    /**
     * 根据主键ID删除记录
     * @param id
     */
    int deleteById(Long id);

    /**
     * 根据主键进行查询
     * @param id
     * @return
     */
    GoodsBrand queryBydId(Long id);
}
