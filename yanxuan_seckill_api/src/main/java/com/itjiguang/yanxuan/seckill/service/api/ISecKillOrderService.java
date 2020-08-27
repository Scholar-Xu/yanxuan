package com.itjiguang.yanxuan.seckill.service.api;

public interface ISecKillOrderService {

    /**
     * 秒杀业务的逻辑处理
     * @param id
     * @param loginName
     * @return
     */
    String createOrder(Long id, String loginName);
}
