package com.itjiguang.yanxuan.solr;

import com.alibaba.fastjson.JSONArray;
import com.itjiguang.yanxuan.viewmodel.GoodsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.support.SolrRepositoryFactory;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateSolrLinstener implements MessageListener {

    @Autowired
    private SolrTemplate solrTemplate;

    @Override
    public void onMessage(Message message) {
        if(message instanceof ObjectMessage){
            ObjectMessage objectMessage = (ObjectMessage)message;
            // 读取信息
            try {
                GoodsInfo goodsInfo = (GoodsInfo) objectMessage.getObject();
                // 商品的规格信息
                String specCheckedList = goodsInfo.getSpecCheckedList();
                List<Map> specList = JSONArray.parseArray(specCheckedList, Map.class);

                // 创建specMap集合
                Map<String, Object> specMap = new HashMap<>();

                for (Map map :specList) {
                    String key = (String) map.get("specName");
                    Object optionValue = map.get("optionValue");

                    specMap.put(key, optionValue);
                }

                goodsInfo.setSpecMap(specMap);

                // 更新索引库
                solrTemplate.saveBean(goodsInfo);
                solrTemplate.commit();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
