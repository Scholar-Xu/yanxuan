package com.itjiguang.yanxuan.account.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itjiguang.yanxuan.account.api.IAccountService;
import com.itjiguang.yanxuan.account.api.IAreaService;
import com.itjiguang.yanxuan.model.SysArea;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/area")
public class AreaController {

    @Reference
    private IAreaService areaService;

    @GetMapping
    public ResponseEntity<List> getByCity(String cityId){
        List<SysArea> areaList = areaService.getByCity(cityId);

        return new ResponseEntity<>(areaList, HttpStatus.OK);
    }
}
