package com.example.thread.test;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

/**
 * 类描述
 *
 * @author abel.huang@zkteco.com
 * @date 2022-06-06 14:07
 * @since 1.0.0
 */
public class Thread2 extends Thread {
    
    @Override
    public void run(){
        System.out.println("这里运行线程代码");
    }

    public static void main(String[] args) {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(3);
//        Thread2 thread2 = new Thread2();
//        thread2.start();
        String a = "";
        System.out.printf("灭了"+CountryEnum.forEach_CountryEnum(1).getRetMessage());
        Semaphore semaphore = new Semaphore(3);
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        
        
 
        new Thread(() -> {

        }, "input your name").start();
        
 

        semaphore.release();
    }
    
    
    
    
}
