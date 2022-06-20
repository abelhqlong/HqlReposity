package com.example.thread.test;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTool<T> {

    //单个线程处理的数据量
    private int singleCount;

    private int listSize;

    private int runSize;

    private List<T> list;

    private CountDownLatch begin, end;

    private ExecutorService executorService;

    private CallBack callBack;

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    //定义启动线程的数量
    public ThreadPoolTool(int singleCount, List<T> list) {
        this.singleCount = singleCount;
        this.list = list;
        if (list != null) {
            this.listSize = list.size();
            //取余如果为整数那么启动的线程数直接相除就行。否则相除+1
            this.runSize = (this.listSize % this.singleCount == 0) ?
                    this.listSize / this.singleCount : this.listSize / this.singleCount + 1;
        }
    }

    public void execute() throws InterruptedException {
        executorService = Executors.newFixedThreadPool(runSize);
        begin = new CountDownLatch(1);
        end = new CountDownLatch(runSize);
        //创建线程
        int startIndex = 0;
        int endIndex = 0;
        List<T> newList = null;
        for (int i = 0; i < runSize; i++) {
            //计算每个线程对应的数据
            if (i < (runSize - 1)) {
                startIndex = i * singleCount;
                endIndex = (i + 1) * singleCount;
                newList = list.subList(startIndex, endIndex);
            } else {
                startIndex = i * singleCount;
                endIndex = listSize;
                newList = list.subList(startIndex, endIndex);
            }
        }

        //创建线程类处理数据
        MyThread<T> myThread2 = new MyThread<T>(newList, begin, end) {

            @Override
            protected void method(List<T> list) {
                callBack.method(list);
            }
        };

        executorService.execute(myThread2);
        //计数器减一
        begin.countDown();
        end.await();
        executorService.shutdown();
    }
}

class UserExecutor {

    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            User user = new User();
            user.setId(i);
            user.setAge(1);
            user.setName("张三" + i);
            users.add(user);
        }

        ThreadPoolTool<String> tool = new ThreadPoolTool(1000, users);
        tool.setCallBack(new CallBack() {

            @Override
            public void method(List list) {
                System.out.println(JSONObject.toJSONString(list));
                //执行插入代码 userDao.insertBatchDemo(list);
            }
        });

        try {
            tool.execute();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

abstract class MyThread<T> implements Runnable {

    private List<T> list;
    private CountDownLatch begin, end;

    public MyThread(List<T> list, CountDownLatch begin, CountDownLatch end) {
        this.list = list;
        this.begin = begin;
        this.end = end;
    }

    @Override
    public void run() {
        try {
            method(list);
            begin.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //计数器减一
            end.countDown();
        }
    }

    protected abstract void method(List<T> list);
}

interface CallBack<T> {
    void method(List<T> list);
}

class User {
    private String name;
    private int age;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
