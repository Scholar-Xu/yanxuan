package com.itjiguang.yanxuan.portal.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itjiguang.yanxuan.account.api.IAccountService;
import com.itjiguang.yanxuan.model.Account;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Reference
    private IAccountService accountService;

    @PostMapping
    public ResponseEntity<String> registAccount(@RequestBody Account account){
        // 调用远程的服务进行数据保存
        String result = accountService.registAccount(account);
        if("OK".equals(result)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }
}
