package com.itjiguang.yanxuan.pay.api;

import com.itjiguang.yanxuan.model.PayLog;

import java.util.Map;

public interface IAlipayService {

    String goAlipay(PayLog payLog);

    Map queryStatus(String payOrderNo);
}
