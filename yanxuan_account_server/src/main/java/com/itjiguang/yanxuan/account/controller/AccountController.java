package com.itjiguang.yanxuan.account.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class AccountController {

    @GetMapping
    public ResponseEntity<Map<String, String>> getLoginAccount(){

//        String remoteUser = request.getRemoteUser();

        // 从Security上下文件中获取
        String remoteUser = SecurityContextHolder.getContext().getAuthentication().getName();

        // 创建返回的结果
        HashMap<String, String> result = new HashMap<>();
        result.put("loginUser", remoteUser);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
