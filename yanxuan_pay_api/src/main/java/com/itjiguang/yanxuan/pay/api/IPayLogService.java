package com.itjiguang.yanxuan.pay.api;

import com.itjiguang.yanxuan.model.PayLog;

import java.util.Map;

public interface IPayLogService {

    /**
     * 根据主键查询支付信息
     * @param payOrderNo
     * @return
     */
    PayLog getByPayOrderNo(String payOrderNo);

    int updatePayLog(Map responseMap);
}
