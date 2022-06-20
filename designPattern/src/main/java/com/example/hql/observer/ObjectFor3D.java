package com.example.hql.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述
 *
 * @author abel.huang@zkteco.com
 * @date 2022-04-27 14:39
 * @since 1.0.0
 */
public class ObjectFor3D implements Subject {
    private List<Ob2server> observers = new ArrayList<>();

    private String msg;

    @Override
    public void registerObserver(Ob2server observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Ob2server observer) {
        int index = observers.indexOf(observer);
        if (index >= 0) {
            observers.remove(index);
        }
    }

    @Override
    public void notifyObservers() {
        for (Ob2server observer : observers) {
            observer.update(msg);
        }
    }

    /**
     * 主题消息更新
     *
     * @param msg
     * @return void
     * @author abel.huang
     * @date 2022/4/27 14:45
     * @since 1.0.0
     */
    public void setMsg(String msg) {
        this.msg = msg;
        notifyObservers();
    }

}
