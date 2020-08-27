package com.itjiguang.yanxuan.account.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itjiguang.yanxuan.account.api.IProvinceService;
import com.itjiguang.yanxuan.model.SysProvince;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/province")
public class ProvinceController {

    @Reference
    private IProvinceService provinceService;

    @GetMapping
    public ResponseEntity<List> get(){
        // 调用远程服务完成所有省信息的查询，并返回
        List<SysProvince> provinceList = provinceService.get();

        return new ResponseEntity<>(provinceList, HttpStatus.OK);
    }
}
