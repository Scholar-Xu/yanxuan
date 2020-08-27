package com.itjiguang.yanxuan.solr;

import com.alibaba.fastjson.JSONArray;
import com.itjiguang.yanxuan.mapper.GoodsSpuMapper;
import com.itjiguang.yanxuan.model.GoodsSpu;
import com.itjiguang.yanxuan.model.GoodsSpuExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品信息从mysql查询出来，然后保存到Solr中
 */
public class GoodsImportService {

    @Autowired
    private GoodsSpuMapper goodsSpuMapper;
    @Autowired
    private SolrTemplate solrTemplate;

    /**
     * 查询所有的商品的信息
     * @return
     */
    private List<GoodsSpu> queryAll(){
        // 创建查询条件
        GoodsSpuExample goodsSpuExample = new GoodsSpuExample();
        // 审核通过的商品信息
        // goodsSpuExample.createCriteria().andStatusEqualTo("1");
        // 查询所有审核通过的商品
        List<GoodsSpu> goodsSpuList = goodsSpuMapper.selectByExample(goodsSpuExample);

        return goodsSpuList;
    }


    public void importToSolr(){
        // 查询所有商品
        List<GoodsSpu> goodsSpuList = this.queryAll();

        // 处理每一个商品中的规格信息
        for (GoodsSpu goodsSpu : goodsSpuList) {
            // 商品的规格信息
            String specCheckedList = goodsSpu.getSpecCheckedList();
            List<Map> specList = JSONArray.parseArray(specCheckedList, Map.class);

            // 创建specMap集合
            Map<String, Object> specMap = new HashMap<>();
            if(specList == null){
                continue;
            }
            for (Map map :specList) {
                String key = (String) map.get("specName");
                Object optionValue = map.get("optionValue");

                specMap.put(key, optionValue);
            }

            goodsSpu.setSpecMap(specMap);
        }

        // 保存批量的就可以
        solrTemplate.saveBeans(goodsSpuList);

        solrTemplate.commit();
    }
}
