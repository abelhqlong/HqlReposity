package com.hqlguigu.springbootrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

import javax.script.Bindings;
import java.util.HashMap;
import java.util.Map;

public class TtlQueueConfig {
    public static final String X_EXCHANGE= "X";
    public static final String QUEUE_A= "QA";
    public static final String QUEUE_B= "QB";
    public static final String Y_DEAD_LETTER_EXCHANGE= "Y";
    public static final String DEAD_LETTER_QUEUE = "QD";

    @Bean("xExchange")
    public DirectExchange xExchange()  {
        return new DirectExchange(X_EXCHANGE);
    }

    @Bean("yExchange")
    public DirectExchange yExchange()  {
        return new DirectExchange(Y_DEAD_LETTER_EXCHANGE);
    }

    @Bean("queueA")
    public Queue queueA()
    {
        Map<String, Object> args = new HashMap<>(3);
        args.put("x-dead-letter-exchange",Y_DEAD_LETTER_EXCHANGE);
        args.put("x-dead-letter-routing-key","YD");
        args.put("x-message-ttl",10000);
        return QueueBuilder.durable(QUEUE_A).withArguments(args).build();
    }

    @Bean("queueB")
    public Queue queueB()
    {
        Map<String, Object> args = new HashMap<>(3);
        args.put("x-dead-letter-exchange",Y_DEAD_LETTER_EXCHANGE);
        args.put("x-dead-letter-routing-key","YD");
        args.put("x-message-ttl",40000);
        return QueueBuilder.durable(QUEUE_B).withArguments(args).build();
    }
    public Binding queueaBindingX(@Qualifier("queueB")Queue queueB, @Qualifier("xExchange")DirectExchange directExchange){
        return BindingBuilder.bind(queueB).to(directExchange).with("Xb");
    }

    @Bean
    public Binding queuebBindingX(@Qualifier("queueB") Queue queue1B,
                                  @Qualifier("xExchange") DirectExchange xExchange){
        return BindingBuilder.bind(queue1B).to(xExchange).with("XB");
    }
    
    //声明死信队列 QD
    @Bean("queueD")
    public Queue queueD() {
        return new Queue(DEAD_LETTER_QUEUE);
    }

    //声明死信队列 QD 绑定关系
    @Bean
    public Binding deadLetterBindingQAD(@Qualifier("queueD") Queue queueD,
                                        @Qualifier("yExchange") DirectExchange yExchange){
        return BindingBuilder.bind(queueD).to(yExchange).with("YD");
    }

}
