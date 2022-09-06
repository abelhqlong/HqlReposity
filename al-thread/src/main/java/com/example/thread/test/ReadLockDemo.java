package com.example.thread.test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 类描述
 *
 * @author abel.huang@zkteco.com
 * @date 2022-06-09 14:47
 * @since 1.0.0
 */


class Mycache {
    private volatile Map<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        rwLock.writeLock().lock();
        try {
            System.out.printf(Thread.currentThread().getName() + "\t 正在写入" + key);
            map.put(key, value);
            System.out.printf(Thread.currentThread().getName() + "\t 写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwLock.writeLock().unlock();
        }
    }


    public void get(String key) {
        rwLock.readLock().lock();
        try {
            System.out.printf(Thread.currentThread().getName() + "\t 正在获取中" + key);
            Object value = map.get(key);
            System.out.printf(Thread.currentThread().getName() + "\t 获取完成" + value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwLock.readLock().unlock();
        }
    }

}

public class ReadLockDemo {
    public static void main(String[] args) {
        Mycache mycache = new Mycache();
        for (int i = 1; i <= 5; i++) {
            final int temap = i;
            new Thread(() -> {
                mycache.put(temap + "", temap + "");
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++) {
            final int temap = i;
            new Thread(() -> {
                mycache.get(temap + "");
            }, String.valueOf(i)).start();
        }

    }
}

   
