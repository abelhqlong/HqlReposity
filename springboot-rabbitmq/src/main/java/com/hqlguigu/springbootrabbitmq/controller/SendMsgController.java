package com.hqlguigu.springbootrabbitmq.controller;

/**
 * 类描述
 *
 * @author abel.huang@zkteco.com
 * @date 2022-06-23 18:05
 * @since 1.0.0
 */
@Slf4j
@RequestMapping("ttl")
@RestController
public class SendMsgController {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @GetMapping("sendMsg/{message}")
    public void sendMsg(@PathVariable String message){
        log.info("当前时间：{},发送一条信息给两个 TTL 队列:{}", new Date(), message);
        rabbitTemplate.convertAndSend("X", "XA", "消息来自 ttl 为 10S 的队列: "+message);
        rabbitTemplate.convertAndSend("X", "XB", "消息来自 ttl 为 40S 的队列: "+message);
    }
}
