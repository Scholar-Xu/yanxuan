package com.itjiguang.yanxuan.detail.service.impl;

import com.itjiguang.yanxuan.detail.api.IGoodsDetailService;
import com.itjiguang.yanxuan.mapper.GoodsSkuMapper;
import com.itjiguang.yanxuan.mapper.GoodsSpuMapper;
import com.itjiguang.yanxuan.model.GoodsSku;
import com.itjiguang.yanxuan.model.GoodsSkuExample;
import com.itjiguang.yanxuan.model.GoodsSpu;
import com.itjiguang.yanxuan.viewmodel.GoodsInfo;
import freemarker.template.Template;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

@Service
public class GoodsDetailServiceImpl implements IGoodsDetailService {

    @Autowired
    private FreeMarkerConfigurer configurer;
    @Autowired
    private GoodsSpuMapper goodsSpuMapper;
    @Autowired
    private GoodsSkuMapper goodsSkuMapper;

    @Value("${pagePath}")
    private String pagePath;
    /**
     * 获取模板
     * 根据商品的主键ID读取商品的信息
     * 生成html文件
     * @param goodsId
     */
    @Override
    public void createHtml(Long goodsId) throws Exception {
        // 获取模板
        Template template = configurer.getConfiguration().getTemplate("goods_detail.ftl");
        // 获取商品的信息
        GoodsSpu goodsSpu = goodsSpuMapper.selectByPrimaryKey(goodsId);
        // 获取商品SKU的信息
        GoodsSkuExample goodsSkuExample = new GoodsSkuExample();
        goodsSkuExample.createCriteria().andGoodsIdEqualTo(goodsId);
        List<GoodsSku> skuList = goodsSkuMapper.selectByExample(goodsSkuExample);

        // 创建关联SKU信息的GoodsInfo
        GoodsInfo goodsInfo = new GoodsInfo();
        BeanUtils.copyProperties(goodsSpu, goodsInfo);
        goodsInfo.setSkuList(skuList);

        // 判断磁盘的目录是否存在，如果不存在就创建出来
        File file = new File(pagePath);
        if(!file.exists()){
            file.mkdir();
        }

        // 设置文件输出的目录是D:/yanxuan_goods/; 文件名称 {ID}.html
        FileWriter fileWriter = new FileWriter(pagePath + goodsId + ".html");
        // 生成html文件
        template.process(goodsInfo, fileWriter);

        // 关闭文件的输出流
        fileWriter.close();
    }

    @Override
    public boolean deleteHtml(Long goodsId) {

        File targetFile = new File(pagePath + goodsId + ".html");

        if(targetFile.exists()){
            targetFile.delete();
        }

        return true;
    }
}
