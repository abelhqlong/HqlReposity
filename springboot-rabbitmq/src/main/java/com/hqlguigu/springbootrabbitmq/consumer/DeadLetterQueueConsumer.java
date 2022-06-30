package com.hqlguigu.springbootrabbitmq.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

/**
 * 类描述
 *
 * @author abel.huang@zkteco.com
 * @date 2022-06-23 18:17
 * @since 1.0.0
 */
@Slf4j
@Component
public class DeadLetterQueueConsumer {
        @RabbitListener(queues = "QD")
        public void receiveD(Message message, Channel channel) throws IOException {
            String msg = new String(message.getBody());
            log.info("当前时间:{},收到死信队列信息{}", new Date().toString(), msg);
        }

}
