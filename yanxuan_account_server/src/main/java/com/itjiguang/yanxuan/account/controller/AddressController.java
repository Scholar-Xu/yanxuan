package com.itjiguang.yanxuan.account.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itjiguang.yanxuan.account.api.IAddressService;
import com.itjiguang.yanxuan.model.AccountAddress;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Reference
    private IAddressService addressService;

    @PostMapping
    public ResponseEntity save(@RequestBody AccountAddress accountAddress){
        // 当前登录的用户名
        String loginName = SecurityContextHolder.getContext().getAuthentication().getName();

        int result = addressService.save(loginName, accountAddress);

        if(result>0){
            return new ResponseEntity(HttpStatus.CREATED);
        }else{
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
