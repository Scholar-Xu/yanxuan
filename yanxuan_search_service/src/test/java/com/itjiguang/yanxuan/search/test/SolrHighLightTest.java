package com.itjiguang.yanxuan.search.test;

import com.itjiguang.yanxuan.model.GoodsSpu;
import com.itjiguang.yanxuan.viewmodel.GoodsInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.HighlightOptions;
import org.springframework.data.solr.core.query.SimpleFilterQuery;
import org.springframework.data.solr.core.query.SimpleHighlightQuery;
import org.springframework.data.solr.core.query.result.HighlightEntry;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@ContextConfiguration(locations = "classpath:spring/spring-solr.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SolrHighLightTest {

    @Autowired
    private SolrTemplate solrTemplate;

    @Test
    public void testHLquery(){
        Criteria contains = new Criteria("goods_keywords").contains("手机");
        // 创建查询条件
        SimpleHighlightQuery highlightQuery = new SimpleHighlightQuery(contains);
        // 设置高亮查询操作
        HighlightOptions highlightOptions = new HighlightOptions();
        highlightOptions.addField("goods_label");
        highlightOptions.setSimplePrefix("<em style='color: red'>");
        highlightOptions.setSimplePostfix("</em>");

        highlightQuery.setHighlightOptions(highlightOptions);

        // 过滤条件
        Criteria specCriteria = new Criteria("goods_spec_机身内存").is("128GB");
        SimpleFilterQuery specFilterQuery = new SimpleFilterQuery(specCriteria);

        highlightQuery.addFilterQuery(specFilterQuery);

        // 高亮查询
        HighlightPage<GoodsSpu> highlightPage = solrTemplate.queryForHighlightPage(highlightQuery, GoodsSpu.class);

        // 获取返回的结果
        List<HighlightEntry<GoodsSpu>> entryList = highlightPage.getHighlighted();

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

            System.out.println(goodsSpu);
        }
    }
}
