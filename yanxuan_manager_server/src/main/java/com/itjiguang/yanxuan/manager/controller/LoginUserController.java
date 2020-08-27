package com.itjiguang.yanxuan.manager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/loginUser")
public class LoginUserController {

    @GetMapping
    public ResponseEntity<Map> getLoginUserName(){
        // 从SpringSecurity中获取登录的用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        // 构建返回结构
        HashMap<String, String> result = new HashMap<>();
        result.put("username", username);
        // 返回结构
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
