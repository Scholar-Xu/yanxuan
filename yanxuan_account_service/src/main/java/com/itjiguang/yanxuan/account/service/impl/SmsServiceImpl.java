package com.itjiguang.yanxuan.account.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itjiguang.yanxuan.account.api.ISmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class SmsServiceImpl implements ISmsService {

    @Autowired
    private JmsTemplate jmsTemplate;
    @Value("${sms.signName}")
    private String signName;
    @Value("${sms.templateCode}")
    private String templateCode;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void sendMessage(String phoneNumbers) {

        // 创建验证码 1.000102352
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        for(int i = 1; i<=6; i++){
            int num = random.nextInt(10); // [0,10)
            stringBuffer.append(num);
        }
        // 获取组装后的验证码
        String checkCode = stringBuffer.toString();

        // 保存验证码
        redisTemplate.boundHashOps("smsCode").put(phoneNumbers, checkCode);

        // 发送消息
        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                MapMessage mapMessage = session.createMapMessage();
                // 设置值
                mapMessage.setString("PhoneNumbers", phoneNumbers);
                mapMessage.setString("SignName", signName);
                mapMessage.setString("TemplateCode", templateCode);
                mapMessage.setString("TemplateParam", "{\"code\":\""+checkCode+"\"}");

                return mapMessage;
            }
        });

    }
}
