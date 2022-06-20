package com.example.thread.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

// 自定义@Async的线程池
@EnableAsync
@Configuration
public class TaskPoolConfig {

    @Bean("taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程数 10
        executor.setCorePoolSize(10);
        //线程池最大的容量数
        executor.setMaxPoolSize(20);
        //阻塞队列的容量
        executor.setQueueCapacity(100);
        //非核心线程存活时间
        executor.setKeepAliveSeconds(60);
        //自定义线程名称
        executor.setThreadNamePrefix("taskExecutor-");
        //默认拒绝策略 超过处理量则直接抛错
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        return executor;
    }
}

//业务类
@Component
@Slf4j
class OrderService {
    /**
     * 导出报表的业务
     */
    @Async("taskExecutor") //此处必须要使用自定义线程池的beanName
    public void exportExcel(){
        log.info("后台开始处理导出报表的业务，线程名:{}",Thread.currentThread().getName());
        long start = System.currentTimeMillis();
        //1.添加用户下载记录表初始数据

        //2.生成订单报表数据

        //3.将报表上传到FastDFS上

        //4.报表上传成功后更新用户下载记录的状态和url

        try {
            //模拟处理以上业务的耗时
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("后台处理报表成功，耗时：" + (System.currentTimeMillis() - start) + "ms,线程名："+Thread.currentThread().getName());
    }
}

//控制层
@RestController
@RequestMapping("/order")
@Slf4j
class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/export")
    public String exportExcel() {
        log.info("用户执行了导出报表功能。。。线程名:{}",Thread.currentThread().getName());
        long start = System.currentTimeMillis();
        //异步导出报表数据
        orderService.exportExcel();
        log.info("在后台处理导出业务，并响应给用户 ，耗时为：{} ms,线程名：{}",System.currentTimeMillis()-start,Thread.currentThread().getName());
        return "ok";
    }

    public static void main(String[] args) {
        
    }
}

