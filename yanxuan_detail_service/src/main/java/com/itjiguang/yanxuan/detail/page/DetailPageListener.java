package com.itjiguang.yanxuan.detail.page;

import com.itjiguang.yanxuan.detail.api.IGoodsDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Component("detailPageListener")
public class DetailPageListener implements MessageListener {

    @Autowired
    private IGoodsDetailService goodsDetailService;
    @Override
    public void onMessage(Message message) {
        if(message instanceof TextMessage){
            TextMessage textMessage = (TextMessage) message;
            try {
                String goodsIdStr = textMessage.getText();
                // 主键ID
                Long id = Long.valueOf(goodsIdStr);

                goodsDetailService.createHtml(id);
            } catch (JMSException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
