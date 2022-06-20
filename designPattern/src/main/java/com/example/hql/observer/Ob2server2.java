package com.example.hql.observer;

/**
 * 类描述
 *
 * @author abel.huang@zkteco.com
 * @date 2022-04-27 14:47
 * @since 1.0.0
 */
public class Ob2server2 implements Ob2server {
    
    private Subject subject;
    @Override
    public void update(String msg) {
        System.out.println("observer2 得到 3D 号码 -->" + msg + "我要告诉舍友们。");
    }

    public Ob2server2(Subject subject)
    {
        this.subject = subject;
        subject.registerObserver(this);
    }

}
