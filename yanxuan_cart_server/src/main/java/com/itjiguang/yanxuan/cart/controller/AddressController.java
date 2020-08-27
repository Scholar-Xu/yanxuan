package com.itjiguang.yanxuan.cart.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itjiguang.yanxuan.account.api.IAddressService;
import com.itjiguang.yanxuan.model.AccountAddress;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Reference
    private IAddressService addressService;

    @GetMapping
    public ResponseEntity<List> getAddress(){
        // 当前登录人
        String loginName = SecurityContextHolder.getContext().getAuthentication().getName();
        //
        List<AccountAddress> addressList = addressService.get(loginName);

        return new ResponseEntity<>(addressList, HttpStatus.OK);
    }
}
