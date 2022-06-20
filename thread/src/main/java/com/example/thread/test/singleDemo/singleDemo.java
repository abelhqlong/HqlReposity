package com.example.thread.test.singleDemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 类描述
 *
 * @author abel.huang@zkteco.com
 * @date 2022-06-07 16:50
 * @since 1.0.0
 */
public class singleDemo {
    private static singleDemo instance = null;
    private singleDemo(){
        System.out.println(Thread.currentThread().getName()+"\t 我是构造方法");
    }
    
    public static singleDemo getInstance()
    {
        if(instance == null)
        {
            instance = new singleDemo();
        }
        return instance;
    }

    public static void main(String[] args) {

        test();
        test2();
        //原子引用线程
        AtomicReference<Thread> atomicReference = new AtomicReference<>();
     


        System.out.println(singleDemo.getInstance() == singleDemo.getInstance());
        System.out.println(singleDemo.getInstance() == singleDemo.getInstance());
        System.out.println(singleDemo.getInstance() == singleDemo.getInstance());
    }

    private static void test2() {
        Lock lock = new ReentrantLock(true);
        lock.lock();
        lock.unlock();
    }

    private static void test() {
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i <3 ; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println("args = " + list);
            },String.valueOf(i)).start();
        }
    }
}
