package com.example.hql.singleton;

/**
 * 类描述
 *
 * @author abel.huang@zkteco.com
 * @date 2022-04-28 15:05
 * @since 1.0.0
 */
public class Singleton {
    private static Singleton instance = null;
//    static {
//        instance = new Singleton();
//    }

    private Singleton() {
    }

    ;

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

}
