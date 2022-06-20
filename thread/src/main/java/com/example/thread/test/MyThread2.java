package com.example.thread.test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 类描述
 *
 * @author abel.huang@zkteco.com
 * @date 2022-06-06 14:17
 * @since 1.0.0
 */
public class MyThread2 implements Runnable {
    @Override
    public void run() {
        System.out.println("这里运行线程代码");

    }

    public static void main(String[] args) {
        MyThread2 myThread2 = new MyThread2();
        myThread2.run();
        
        AtomicInteger atomicInteger = new AtomicInteger();
//        atomicInteger.getAndAdd()
        
    }
}
