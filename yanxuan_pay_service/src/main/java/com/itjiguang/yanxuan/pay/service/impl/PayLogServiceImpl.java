package com.itjiguang.yanxuan.pay.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itjiguang.yanxuan.mapper.PayLogMapper;
import com.itjiguang.yanxuan.model.PayLog;
import com.itjiguang.yanxuan.pay.api.IPayLogService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Service
public class PayLogServiceImpl implements IPayLogService {

    @Autowired
    private PayLogMapper payLogMapper;

    @Override
    public PayLog getByPayOrderNo(String payOrderNo) {
        return payLogMapper.selectByPrimaryKey(payOrderNo);
    }

    @Override
    public int updatePayLog(Map responseMap) {
        int result = 0;
        try {
            // 创建更新的日志信息
            PayLog payLog = new PayLog();
            payLog.setPayOrderNo(responseMap.get("out_trade_no").toString());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

            Date payDate = simpleDateFormat.parse(responseMap.get("send_pay_date").toString());
            payLog.setPayDate(payDate);
            payLog.setTradeStatus("1");
            payLog.setTradeNo(responseMap.get("trade_no").toString());

            result = payLogMapper.updateByPrimaryKeySelective(payLog);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
