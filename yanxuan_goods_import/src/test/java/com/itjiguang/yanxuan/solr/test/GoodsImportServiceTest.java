package com.itjiguang.yanxuan.solr.test;

import com.itjiguang.yanxuan.solr.GoodsImportService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = "classpath*:spring/spring-*.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class GoodsImportServiceTest {

    @Autowired
    private GoodsImportService goodsImportService;

    @Test
    public void testImport(){
        goodsImportService.importToSolr();
    }
}
