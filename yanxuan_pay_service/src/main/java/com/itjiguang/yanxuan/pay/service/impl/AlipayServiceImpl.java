package com.itjiguang.yanxuan.pay.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.itjiguang.yanxuan.config.AlipayConfig;
import com.itjiguang.yanxuan.model.PayLog;
import com.itjiguang.yanxuan.pay.api.IAlipayService;

import java.util.HashMap;
import java.util.Map;

@Service
public class AlipayServiceImpl implements IAlipayService {
    @Override
    public String goAlipay(PayLog payLog) {
        // 初始化返回结果
        String result = "";
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        // 设置业务参数
        Map paramMap = new HashMap();
        paramMap.put("out_trade_no", payLog.getPayOrderNo());
        paramMap.put("total_amount",payLog.getTotalFee().doubleValue()/100);
        paramMap.put("subject","订单支付测试");
        paramMap.put("product_code","FAST_INSTANT_TRADE_PAY");

        String paramContent  = JSON.toJSONString(paramMap);
        // 设置请求参数中的业务参数
        alipayRequest.setBizContent(paramContent);

        try {
            result = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Map queryStatus(String payOrderNo) {
        // 返回内容初始化
        Map<Object, Object> responseMap = new HashMap<>();
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();

        // 创建业务参数
        HashMap<Object, Object> paramsContent = new HashMap<>();
        paramsContent.put("out_trade_no", payOrderNo);
        alipayRequest.setBizContent(JSON.toJSONString(paramsContent));

        try {
            String result = alipayClient.execute(alipayRequest).getBody();
            Map resultMap = JSON.parseObject(result, Map.class);

            responseMap = (Map)resultMap.get("alipay_trade_query_response");
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return responseMap;
    }
}
