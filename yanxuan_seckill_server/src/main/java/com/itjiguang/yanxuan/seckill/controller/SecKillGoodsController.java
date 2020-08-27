package com.itjiguang.yanxuan.seckill.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itjiguang.yanxuan.seckill.service.api.ISecKillGoodsService;
import com.itjiguang.yanxuan.viewmodel.SecKillGoodsInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seckillGoods")
public class SecKillGoodsController {

    @Reference
    private ISecKillGoodsService goodsService;

    @GetMapping("/{id}")
    public ResponseEntity<SecKillGoodsInfo> queryById(@PathVariable("id") Long id){
        // 调用远程服务完成信息的查询
        SecKillGoodsInfo goodsInfo = goodsService.queryById(id);

        return new ResponseEntity(goodsInfo, HttpStatus.OK);
    }
}
