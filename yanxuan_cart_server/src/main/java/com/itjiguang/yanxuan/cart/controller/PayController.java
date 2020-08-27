package com.itjiguang.yanxuan.cart.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itjiguang.yanxuan.model.PayLog;
import com.itjiguang.yanxuan.pay.api.IAlipayService;
import com.itjiguang.yanxuan.pay.api.IPayLogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class PayController {

    @Reference
    private IPayLogService payLogService;
    @Reference
    private IAlipayService alipayService;

    @GetMapping(value = "/pay", produces = "text/html; charset=utf-8")
    @ResponseBody
    public String orderPay(String payOrderNo){
        // 初始化响应内容
        String content="";

        // 调用远程服务完成支付信息查询
        PayLog payLog = payLogService.getByPayOrderNo(payOrderNo);
        // 根据方式选择支付接口的调用
        if(payLog!=null ){
            if(payLog.getPayType()!=null && "0".equals(payLog.getPayType())){
                // 支付宝支付
                content = alipayService.goAlipay(payLog);

            }else{
                // 其他的支付方式
                content = "<h1>其他支付方式</h1>";
            }
        }else{
            throw new RuntimeException("支付信息存在错误，稍后查看订单信息");
        }

        return content;
    }
}
