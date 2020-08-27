package com.itjiguang.yanxuan.cart.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.itjiguang.yanxuan.alipay.AlipayResponse;
import com.itjiguang.yanxuan.config.AlipayConfig;
import com.itjiguang.yanxuan.pay.api.IAlipayService;
import com.itjiguang.yanxuan.pay.api.IPayLogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping
public class AlipayController {

    @Reference
    private IAlipayService alipayService;
    @Reference
    private IPayLogService payLogService;

    @GetMapping("/alipay_return")
    public ModelAndView retrunUrl(AlipayResponse alipayResponse){
        // 参数的转换
        String responseString = JSON.toJSONString(alipayResponse);
        Map params = JSON.parseObject(responseString, Map.class);

        ModelAndView modelAndView = new ModelAndView();

        try {
            boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

            if(signVerified){
                // 进行页面跳转，成功
                modelAndView.addObject("payOrderNo", alipayResponse.getOut_trade_no());
                modelAndView.setViewName("redirect:/alipay_status");
            }else{
                // 跳转到失败
                modelAndView.setViewName("/goods_pay_failed.html");
            }

        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        return modelAndView;
    }

    @GetMapping("/alipay_status")
    public String queryStatus(String payOrderNo, Model model){
        String url = "/goods_pay_failed.html";
        // 调用远程服务完成订单支付信息的查询，只要是成功的就更新支付信息
        Map responseMap = alipayService.queryStatus(payOrderNo);
        if("TRADE_SUCCESS".equals(responseMap.get("trade_status"))){
            // 更新支付信息
            int result = payLogService.updatePayLog(responseMap);
            if(result>0){
                url = "/goods_pay_success.html";
            }else{
                url = "/goods_pay_failed.html";
            }
        }else{
            url = "/goods_pay_failed.html";
        }

        model.addAttribute("payOrderNo", payOrderNo);
        model.addAttribute("payDate", responseMap.get("send_pay_date").toString());
        model.addAttribute("payType", "支付宝支付");


        return url;
    }

}
