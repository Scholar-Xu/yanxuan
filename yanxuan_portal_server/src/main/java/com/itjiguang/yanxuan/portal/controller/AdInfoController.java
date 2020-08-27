package com.itjiguang.yanxuan.portal.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itjiguang.yanxuan.ad.api.IAdInfoService;
import com.itjiguang.yanxuan.model.AdInfo;
import com.itjiguang.yanxuan.result.PageResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/adInfo")
public class AdInfoController {

    @Reference
    private IAdInfoService adInfoService;

    @GetMapping
    public ResponseEntity<PageResult<AdInfo>> query(AdInfo adInfo){
        Integer currentPage =1;
        Integer pageSize = Integer.MAX_VALUE;
        // 调用远程服务
        PageResult pageResult = adInfoService.pageQuery(currentPage, pageSize, adInfo);
        return new ResponseEntity<>(pageResult, HttpStatus.OK);
    }
}
