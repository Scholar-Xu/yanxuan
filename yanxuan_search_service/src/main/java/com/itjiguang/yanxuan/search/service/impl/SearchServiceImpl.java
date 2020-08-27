package com.itjiguang.yanxuan.search.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.itjiguang.yanxuan.mapper.GoodsCategoryBrandSpecMapper;
import com.itjiguang.yanxuan.mapper.GoodsCategoryMapper;
import com.itjiguang.yanxuan.mapper.GoodsSpecOptionMapper;
import com.itjiguang.yanxuan.model.*;
import com.itjiguang.yanxuan.search.api.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.*;
import org.springframework.data.solr.core.query.result.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SearchServiceImpl implements ISearchService {

    @Autowired
    private SolrTemplate solrTemplate;

    @Override
    public Map<String, Object> query(Map queryParams) {
        // 创建返回的最终结果
        Map<String, Object> result = new HashMap<>();
        // 根据关键词查询商品
        this.queryGoods(queryParams, result);
        // 根据关键词查询商品类目的信息
        this.queryCategory(queryParams, result);
        // 根据类目信息查询关联的品牌和规格信息
        this.queryBrandAndSpec(queryParams, result);

        return result;
    }

    /**
     * 根据关键词进行查询关联的类目的信息
     * @param queryParams
     * @param resultMap
     */
    private void queryCategory(Map queryParams, Map resultMap){
        // 类目信息
        ArrayList<String> categoryList = new ArrayList<>();
        // 查询请求中的关键词
        String keywords = (String) queryParams.get("keywords");
        keywords = keywords.replaceAll(" ", "");
        // 创建查询条件
        Criteria criteria = new Criteria("goods_keywords").is(keywords);
        SimpleQuery simpleQuery = new SimpleQuery(criteria);
        // 设置分组的相关操作
        GroupOptions groupOptions = new GroupOptions();
        groupOptions.addGroupByField("goods_category");
        simpleQuery.setGroupOptions(groupOptions);
        // 执行分组的查询
        GroupPage<GoodsSpu> groupPage = solrTemplate.queryForGroupPage(simpleQuery, GoodsSpu.class);

        // 根据fieldname获取分页结果
        GroupResult<GoodsSpu> groupResult = groupPage.getGroupResult("goods_category");
        // 获取分组的实体对象
        Page<GroupEntry<GoodsSpu>> groupEntryPage = groupResult.getGroupEntries();
        // 具体的分页信息
        List<GroupEntry<GoodsSpu>> groupEntryList = groupEntryPage.getContent();

        for (GroupEntry<GoodsSpu> groupEntry:groupEntryList) {
            String groupValue = groupEntry.getGroupValue();
            categoryList.add(groupValue);
        }

        resultMap.put("category", categoryList);
    }

    /**
     * 根据关键进行商品查询，并且关键字进行高亮显示
     * @param queryParams
     * @param resultMap
     */
    private void queryGoods(Map queryParams, Map resultMap){
        // 查询请求中的关键词
        String keywords = (String) queryParams.get("keywords");
        keywords = keywords.replaceAll(" ", "");
        // 创建条件
        Criteria criteria = new Criteria("goods_keywords").is(keywords);
        SimpleHighlightQuery highlightQuery = new SimpleHighlightQuery(criteria);
        // 设置高亮查询操作
        HighlightOptions highlightOptions = new HighlightOptions();
        highlightOptions.addField("goods_label");
        highlightOptions.setSimplePrefix("<em style='color: red'>");
        highlightOptions.setSimplePostfix("</em>");

        highlightQuery.setHighlightOptions(highlightOptions);
        // 创建过滤条件-category
        if(queryParams.get("category")!=null && !"".equals(queryParams.get("category"))){
            Criteria categoryCriteria = new Criteria("goods_category").is(queryParams.get("category"));
            SimpleFilterQuery simpleFilterQuery = new SimpleFilterQuery(categoryCriteria);
            highlightQuery.addFilterQuery(simpleFilterQuery);
        }

        // 创建过滤条件-brand
        if(queryParams.get("brand")!=null && !"".equals(queryParams.get("brand"))){
            Criteria brandCriteria = new Criteria("goods_brand").is(queryParams.get("brand"));
            SimpleFilterQuery simpleFilterQuery = new SimpleFilterQuery(brandCriteria);
            highlightQuery.addFilterQuery(simpleFilterQuery);
        }

        // 创建过滤条件-spec
        if(queryParams.get("spec")!=null && !"{}".equals(queryParams.get("spec"))){
            //获取到规格的条件
            String spec = (String) queryParams.get("spec");
            Map<String, String> specMap = JSON.parseObject(spec, Map.class);
            for (String key :specMap.keySet()) {
                String optionValue = specMap.get(key);
                Criteria criteria1 = new Criteria("goods_spec_" + key).is(optionValue);
                SimpleFilterQuery simpleFilterQuery = new SimpleFilterQuery(criteria1);
                highlightQuery.addFilterQuery(simpleFilterQuery);
            }
        }

        // 创建过滤条件-price
        if(queryParams.get("price")!=null && !"".equals(queryParams.get("price"))){
            // 原始的价格区间 100-399
            String price = queryParams.get("price").toString();
            // 切割获取得到具体的区间上、下限
            String[] priceArray = price.split("-"); // {"100","399"}
            // 下限的过滤条件
            Criteria goods_price_greater = new Criteria("goods_price_d").greaterThanEqual(priceArray[0]);
            SimpleFilterQuery greaterFilter = new SimpleFilterQuery(goods_price_greater);
            highlightQuery.addFilterQuery(greaterFilter);

            if(!price.contains("-*")) {
                // 上限的过滤条件
                Criteria goods_price_less = new Criteria("goods_price_d").lessThanEqual(priceArray[1]);
                SimpleFilterQuery lessFilter = new SimpleFilterQuery(goods_price_less);
                highlightQuery.addFilterQuery(lessFilter);
            }
        }

        // 获取请求中的分页参数
        Integer currentPage = Integer.valueOf(queryParams.get("pageNum").toString());
        Integer pageSize = Integer.valueOf(queryParams.get("pageSize").toString());

        // 设置查询请求中的分页参数
        highlightQuery.setPageRequest(new PageRequest(currentPage-1, pageSize));

        // 设置多个关键字之间的逻辑关系, 逻辑与的关系
        highlightQuery.setDefaultOperator(Query.Operator.AND);

        String fieldName = "";
        Sort sort = null;
        // 设置最终的排序方式
        if(queryParams.get("sortField")!=null && "price".equals(queryParams.get("sortField"))){
            fieldName = "goods_price_d";
        }
        if(queryParams.get("sortType")!=null && "DESC".equals(queryParams.get("sortType"))){
             sort = new Sort(new Sort.Order(Sort.Direction.DESC, fieldName));
        }
        if(queryParams.get("sortType")!=null && "ASC".equals(queryParams.get("sortType"))){
            sort = new Sort(new Sort.Order(Sort.Direction.ASC, fieldName));
        }
        if(sort!=null){
            highlightQuery.addSort(sort);
        }


        // 进行查询
        HighlightPage<GoodsSpu> highlightPage = solrTemplate.queryForHighlightPage(highlightQuery, GoodsSpu.class);

        // 获取返回的结果
        List<HighlightEntry<GoodsSpu>> entryList = highlightPage.getHighlighted();

        // 返回的结果
        ArrayList<GoodsSpu> resultList = new ArrayList<>();

        for (HighlightEntry<GoodsSpu> entry : entryList) {
            // 获取文档数据（原始数据）
            GoodsSpu goodsSpu = entry.getEntity();
            // 高亮的信息
            List<HighlightEntry.Highlight> highlights = entry.getHighlights();
            for (HighlightEntry.Highlight highlight : highlights){
                List<String> snipplets = highlight.getSnipplets();
                StringBuffer stringBuffer = new StringBuffer();
                for (String str : snipplets) {
                    stringBuffer.append(str);
                }

                goodsSpu.setLabel(stringBuffer.toString());
            }

            resultList.add(goodsSpu);
        }

        resultMap.put("result", resultList);
        resultMap.put("total", highlightPage.getTotalElements());
        resultMap.put("totalPage",highlightPage.getTotalPages() );
        resultMap.put("keywords", keywords);
    }

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;
    @Autowired
    private GoodsCategoryBrandSpecMapper goodsCategoryBrandSpecMapper;
    @Autowired
    private GoodsSpecOptionMapper goodsSpecOptionMapper;

    /**
     * 查询关联的品牌信息和规格信息
     * 1. 从Redis数据库中 根据 一级类目>二级类目>三级类目 查找对应的value值，即主键ID
     *      redis中的数据只从mysql中读取的（三级类目）
     * 2. 根据类目的主键ID查询关联的品牌和规格信息
     * @param resultMap
     */
    private void queryBrandAndSpec(Map queryParams, Map resultMap){
        // 获取查询条件中的类目的信息
        String category = (String) queryParams.get("category");
        // 获取类目信息
        List<String> categoryList = (List<String>)resultMap.get("category");
        if(category ==null || "".equals(category)){
            if(categoryList.size()>0) {
                // 读取第一个类目的信息
                category = categoryList.get(0);
            }else {
                return ;
            }
        }


        // 从reids中读取关联的品牌信息和规格信息
        List<Map> brandList1 =(List<Map>) redisTemplate.boundHashOps("brandList").get(category);
        List<Map> specList1 =(List<Map>) redisTemplate.boundHashOps("specList").get(category);

        if(brandList1!=null && specList1!=null){
            resultMap.put("brandList", brandList1);
            resultMap.put("specList", specList1);
            System.out.println("从缓存中读取关联的品牌和类目的信息");
            return;
        }

        // 从redis数据库中查找
        Integer categoryId = (Integer) redisTemplate.boundHashOps("categoryList").get(category);
        if(categoryId==null){
            // 从数据库中查询数据并且存放redis中
            // 创建查询三级类目信息的条件
            GoodsCategoryExample goodsCategoryExample = new GoodsCategoryExample();
            goodsCategoryExample.createCriteria().andLevelEqualTo(3);
            // 执行查询
            List<GoodsCategory> goodsCategoryList = goodsCategoryMapper.selectByExample(goodsCategoryExample);
            HashMap<String, Long> categoryMap = new HashMap<>();
            for (GoodsCategory goodsCategory : goodsCategoryList) {
                categoryMap.put(goodsCategory.getStructName()+">"+goodsCategory.getName(), goodsCategory.getId());
            }
            // 保存数据到redis
            redisTemplate.boundHashOps("categoryList").putAll(categoryMap);
            categoryId = categoryMap.get(category).intValue();
        }

        // 查询关联的信息
        GoodsCategoryBrandSpecExample goodsCategoryBrandSpecExample = new GoodsCategoryBrandSpecExample();
        goodsCategoryBrandSpecExample.createCriteria().andCategoryIdEqualTo(new Long(categoryId));
        List<GoodsCategoryBrandSpec> brandSpecList = goodsCategoryBrandSpecMapper.selectByExample(goodsCategoryBrandSpecExample);
        GoodsCategoryBrandSpec goodsCategoryBrandSpec = brandSpecList.get(0);

        // 品牌的信息
        String brands = goodsCategoryBrandSpec.getBrandIds();
        List<Map> brandList = JSON.parseArray(brands, Map.class);

        // 规格的信息
        String specs = goodsCategoryBrandSpec.getSpecIds();
        List<Map> specList = JSON.parseArray(specs, Map.class);
        for (Map specMap : specList) {
            Integer specId = (Integer) specMap.get("id");
            // 创建根据SpecID查询option的条件
            GoodsSpecOptionExample goodsSpecOptionExample = new GoodsSpecOptionExample();
            goodsSpecOptionExample.createCriteria().andSpecIdEqualTo(new Long(specId));
            // 执行查询
            List<GoodsSpecOption> optionList = goodsSpecOptionMapper.selectByExample(goodsSpecOptionExample);
            // 把规格和规格项关联起来
            specMap.put("optionList", optionList);
        }

        resultMap.put("brandList",brandList);
        resultMap.put("specList", specList);

        // 存放数据到redis
        redisTemplate.boundHashOps("brandList").put(category, brandList);
        redisTemplate.boundHashOps("specList").put(category, specList);
    }

}
