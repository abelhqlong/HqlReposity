package com.example.thread.test;

import java.sql.Time;
import java.util.concurrent.*;

/**
 * 类描述
 *
 * @author abel.huang@zkteco.com
 * @date 2022-06-13 13:52
 * @since 1.0.0
 */
public class ThreadExecuter {

    public static void main(String[] args) {

        ExecutorService threadPool = new ThreadPoolExecutor(
                2, 
                5, 
                1L, 
                TimeUnit.SECONDS, 
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

//        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        try {
            for (int i = 0; i < 5; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        } catch (Exception e) {
           e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }

    }
}
