package com.itjiguang.yanxuan.seckill.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itjiguang.yanxuan.model.SecondKillGoods;
import com.itjiguang.yanxuan.seckill.service.api.ISecKillOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/seckillOrder")
public class SecKillOrderController {

    @Reference
    private ISecKillOrderService orderService;

    @PostMapping
    public ResponseEntity createOrder(@RequestBody SecondKillGoods secondKillGoods){
        // 获取当前登录的用户名
        String loginName = SecurityContextHolder.getContext().getAuthentication().getName();
        try{
            // 调用远程服务
            String orderNo = orderService.createOrder(secondKillGoods.getId(), loginName);
            Map<String,String> result = new HashMap<String, String>();
            result.put("orderNo", orderNo);
            return new ResponseEntity(result,HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.OK);
        }
    }
}
