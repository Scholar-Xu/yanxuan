package com.itjiguang.yanxuan.goods.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itjiguang.yanxuan.goods.api.IGoodsCategoryService;
import com.itjiguang.yanxuan.mapper.GoodsCategoryBrandSpecMapper;
import com.itjiguang.yanxuan.mapper.GoodsCategoryMapper;
import com.itjiguang.yanxuan.mapper.GoodsSpecOptionMapper;
import com.itjiguang.yanxuan.model.*;
import com.itjiguang.yanxuan.result.PageResult;
import com.itjiguang.yanxuan.viewmodel.Category;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class GoodsCategoryServiceImpl implements IGoodsCategoryService {

    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;
    @Autowired
    private GoodsCategoryBrandSpecMapper goodsCategoryBrandSpecMapper;
    @Autowired
    private GoodsSpecOptionMapper goodsSpecOptionMapper;

    @Override
    public PageResult<GoodsCategory> pageQuery(Integer currentPage, Integer pageSize, GoodsCategory goodsCategory) {

        // 构建查询条件
        GoodsCategoryExample goodsCategoryExample = new GoodsCategoryExample();
        if(goodsCategory!=null){
            if(!"".equals(goodsCategory.getName()) && goodsCategory.getName()!=null){
                goodsCategoryExample.or().andNameLike("%"+goodsCategory.getName()+"%");
            }else{
                // 当没有查询条件时，才会使用parentID进行查询
                if(goodsCategory.getParentId()!=null){
                    goodsCategoryExample.or().andParentIdEqualTo(goodsCategory.getParentId());
                }
            }
        }
        // 开启分页
        PageHelper.startPage(currentPage, pageSize);
        // 进行查询
        Page<GoodsCategory> pageData = (Page<GoodsCategory>)goodsCategoryMapper.selectByExample(goodsCategoryExample);

        // 构建返回结果
        PageResult<GoodsCategory> pageResult = new PageResult<>();
        pageResult.setTotal(pageData.getTotal());
        pageResult.setResult(pageData.getResult());

        return pageResult;
    }

    @Override
    public int save(Category category) {
        // 设置信息为有效状态
        category.setStatus("0");
        // 设置排序序号时，需要先查询数据库中该类目的子类目存在的数量
        GoodsCategoryExample goodsCategoryExample = new GoodsCategoryExample();
        goodsCategoryExample.or().andParentIdEqualTo(category.getParentId());
        int count = (int)goodsCategoryMapper.countByExample(goodsCategoryExample);
        category.setSortNo(count+1);
        // 保存类目信息
        int insert = goodsCategoryMapper.insert(category);

        // 保存关联的品牌信息、规格信息
        GoodsCategoryBrandSpec relation = category.getRelation();
        relation.setCategoryId(category.getId());
        goodsCategoryBrandSpecMapper.insert(relation);

        return insert;
    }

    @Override
    public Category queryById(Long id) {
        // 根据主键id进行查询类目信息
        GoodsCategory goodsCategory = goodsCategoryMapper.selectByPrimaryKey(id);
        // 查询关联信息
        GoodsCategoryBrandSpecExample goodsCategoryBrandSpecExample = new GoodsCategoryBrandSpecExample();
        goodsCategoryBrandSpecExample.or().andCategoryIdEqualTo(id);
        List<GoodsCategoryBrandSpec> relationList = goodsCategoryBrandSpecMapper.selectByExample(goodsCategoryBrandSpecExample);

        // 构建返回结果
        Category category = new Category();
        BeanUtils.copyProperties(goodsCategory, category);
        if(relationList.size()>0){
            // 关联的品牌和规格信息
            category.setRelation(relationList.get(0));
            // 获取的规格信息
            // [{"id":2,"name":"机身内存"},{"id":4,"name":"运行内存"},{"id":11,"name":"分辨率"},{"id":6,"name":"电池容量"},{"id":18,"name":"颜色"}]
            String specIds = relationList.get(0).getSpecIds();
            List<Map> mapList = JSON.parseArray(specIds, Map.class);
            for (Map tmpMap : mapList ) {
                // 获取规格的id
                Long specId = new Long((Integer) tmpMap.get("id"));
                // 创建查询条件
                GoodsSpecOptionExample goodsSpecOptionExample = new GoodsSpecOptionExample();
                goodsSpecOptionExample.createCriteria().andSpecIdEqualTo(specId);
                // 进行查询
                List<GoodsSpecOption> optionList = goodsSpecOptionMapper.selectByExample(goodsSpecOptionExample);
                // 存放到map中
                tmpMap.put("optionList", optionList);
            }
            // 规格和规格项的信息
            category.setSpecList(mapList);
        }
        return category;
    }

    @Override
    public int update(Category category) {
        // 更新类目信息
        int update = goodsCategoryMapper.updateByPrimaryKey(category);
        // 更新关联的信息
        GoodsCategoryBrandSpec relation = category.getRelation();
        goodsCategoryBrandSpecMapper.updateByPrimaryKey(relation);

        return update;
    }
}
