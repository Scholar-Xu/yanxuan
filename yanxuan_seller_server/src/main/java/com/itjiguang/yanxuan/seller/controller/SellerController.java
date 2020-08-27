package com.itjiguang.yanxuan.seller.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itjiguang.yanxuan.seller.api.SellerInfoService;
import com.itjiguang.yanxuan.viewmodel.SellerInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Reference
    private SellerInfoService sellerInfoService;

    @PostMapping
    public ResponseEntity save(@RequestBody SellerInfo sellerInfo){
        // 调用远程的服务完成数据保存
        int save = sellerInfoService.save(sellerInfo);
        if(save>0){
            return new ResponseEntity(HttpStatus.CREATED);
        }else{
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
