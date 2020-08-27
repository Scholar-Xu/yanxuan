package com.itjiguang.yanxuan.account.api;

public interface ISmsService {

    /**
     * 向MQ服务器中发送消息
     */
    public void sendMessage(String phoneNumbers);
}
