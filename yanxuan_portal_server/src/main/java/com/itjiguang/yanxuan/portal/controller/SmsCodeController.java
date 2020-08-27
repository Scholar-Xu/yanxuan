package com.itjiguang.yanxuan.portal.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itjiguang.yanxuan.account.api.ISmsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms")
public class SmsCodeController {

    @Reference
    private ISmsService smsService;

    @GetMapping("{phone}")
    public ResponseEntity sendSms(@PathVariable("phone") String phoneNumbers){
        // 调用远程服务向MQ发送发送短信的消息
        smsService.sendMessage(phoneNumbers);

        return new ResponseEntity(HttpStatus.OK);
    }
}
