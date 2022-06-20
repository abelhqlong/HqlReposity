package com.example.hql.observer;

/**
 * 类描述
 *
 * @author abel.huang@zkteco.com
 * @date 2022-04-27 14:35
 * @since 1.0.0
 */
public interface Subject {
    /**
     * 注册一个观察着
     *
     * @param observer
     */
    public void registerObserver(Ob2server observer);


    /**
     * 移除一个观察者
     *
     * @param observer
     */
    public void removeObserver(Ob2server observer);

    /**
     * 通知所有的观察着
     */
    public void notifyObservers();
}
