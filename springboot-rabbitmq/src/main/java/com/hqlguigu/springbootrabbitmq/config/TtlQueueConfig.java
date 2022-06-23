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

}
