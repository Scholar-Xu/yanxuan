package com.itjiguang.yanxuan.account.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itjiguang.yanxuan.account.api.ICityService;
import com.itjiguang.yanxuan.model.SysCity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    @Reference
    private ICityService cityService;

    @GetMapping
    public ResponseEntity<List> getByProvince(String provinceId){
        List<SysCity> cityList = cityService.getByProvince(provinceId);

        return new ResponseEntity<>(cityList, HttpStatus.OK);
    }
}
