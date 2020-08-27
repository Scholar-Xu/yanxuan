package com.itjiguang.yanxuan.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itjiguang.yanxuan.model.SellerShop;
import com.itjiguang.yanxuan.result.PageResult;
import com.itjiguang.yanxuan.seller.api.SellerInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Reference
    private SellerInfoService sellerInfoService;

    @GetMapping
    public ResponseEntity<PageResult> query(Integer currentPage, Integer pageSize, SellerShop sellerShop){
        // 分页参数的处理
        if(currentPage == null || pageSize == null){
            currentPage = 1;
            pageSize = Integer.MAX_VALUE;
        }
        // 调用远程的服务查询商家信息
        PageResult<SellerShop> pageResult = sellerInfoService.pageQuery(currentPage, pageSize, sellerShop);
        // 返回结果
        return new ResponseEntity<>(pageResult, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody SellerShop sellerShop){
        int result = sellerInfoService.update(sellerShop);
        if(result>0){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
