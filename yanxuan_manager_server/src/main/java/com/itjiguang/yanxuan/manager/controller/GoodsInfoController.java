package com.itjiguang.yanxuan.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itjiguang.yanxuan.goods.api.IGoodsInfoService;
import com.itjiguang.yanxuan.model.GoodsSpu;
import com.itjiguang.yanxuan.result.PageResult;
import com.itjiguang.yanxuan.viewmodel.GoodsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.*;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@RestController
@RequestMapping("/goods")
public class GoodsInfoController {

    @Reference
    private IGoodsInfoService goodsInfoService;
   /* @Reference
    private IGoodsDetailService goodsDetailService;*/
    @GetMapping
    public ResponseEntity<PageResult<GoodsSpu>> query(Integer currentPage, Integer pageSize, GoodsSpu goodsSpu){
        // 处理分页参数
        if(currentPage==null || pageSize== null){
            currentPage = 1;
            pageSize = Integer.MAX_VALUE;
        }
        // 调用远程的服务进行分页查询
        PageResult<GoodsSpu> pageResult = goodsInfoService.pageQuery(currentPage, pageSize, goodsSpu);

        return new ResponseEntity<>(pageResult, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GoodsInfo> queryById(@PathVariable("id") Long id){
        // 调用远程的服务进行商品的查询
        GoodsInfo goodsInfo = goodsInfoService.queryById(id);
        return new ResponseEntity<>(goodsInfo, HttpStatus.OK);
    }

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    @Qualifier("detailPage")
    private Destination destination;

    @PatchMapping
    public ResponseEntity auditGoods(@RequestBody GoodsSpu goodsSpu){
        // 调用远程服务更新状态
        int result = goodsInfoService.audit(goodsSpu);
        if(goodsSpu.getStatus()!=null && "1".equals(goodsSpu.getStatus())){
            // 更新Solr索引库
            // 商品信息的查询
            GoodsInfo goodsInfo = goodsInfoService.queryById(goodsSpu.getId());
            // 发送消息
            jmsTemplate.send(new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    return session.createObjectMessage(goodsInfo);
                }
            });

            // 生成详情页面
            jmsTemplate.send(destination, new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    return session.createTextMessage(goodsInfo.getId().toString());
                }
            });
            /*try {
                goodsDetailService.createHtml(goodsSpu.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }*/
        }
        if(result>0){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
