package com.itjiguang.yanxuan.quartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

public class OrderReminderJob extends QuartzJobBean {

    @Autowired
    private JmsTemplate jmsTemplate;
    @Value("${sms.signName}")
    private String signName;
    @Value("${sms.templateCode}")
    private String templateCode;

    /**
     * 获取外部传递的参数完成向activemq发送消息
     * @param context
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        // 接收外部传递的手机号
        String phoneNum = context.getMergedJobDataMap().getString("phoneNum");
        String orderNo = context.getMergedJobDataMap().getString("orderNo");

        System.out.println("手机号："+phoneNum);
        System.out.println("订单号："+orderNo);

        /*jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                MapMessage mapMessage = session.createMapMessage();
                // 设置值
                mapMessage.setString("PhoneNumbers", phoneNum);
                mapMessage.setString("SignName", signName);
                mapMessage.setString("TemplateCode", templateCode);
                mapMessage.setString("TemplateParam", "{\"code\":\""+orderNo+"\"}");

                return mapMessage;
            }
        });*/
    }
}
