package com.itjiguang.yanxuan.cart.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itjiguang.yanxuan.order.IOrderInfoService;
import com.itjiguang.yanxuan.viewmodel.OrderInfoViewModel;
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
@RequestMapping("/order")
public class OrderInfoController {

    @Reference
    private IOrderInfoService orderInfoService;

    @PostMapping
    public ResponseEntity<Map> saveOrderInfo(@RequestBody OrderInfoViewModel orderInfoViewModel){
        // 获取当前登录人
        String loginName = SecurityContextHolder.getContext().getAuthentication().getName();
        // 调用远程服务，完成 loginName的订单信息保存
        String payOrderNo = orderInfoService.saveOrderInfo(loginName, orderInfoViewModel);

        HashMap<String, String> resultMap = new HashMap<>();
        resultMap.put("payOrderNo", payOrderNo);

        if(payOrderNo!=null && !"".equals(payOrderNo)){
            return new ResponseEntity(resultMap, HttpStatus.CREATED);
        }else{
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
