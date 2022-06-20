package com.example.hql.observer;

/**
 * 类描述
 *
 * @author abel.huang@zkteco.com
 * @date 2022-04-27 14:50
 * @since 1.0.0
 */
public class Test {
    public static void main(String[] args) {
        ObjectFor3D objectFor3D = new ObjectFor3D();
        //客户1
        Ob2server ob2server1 = new Ob2server1(objectFor3D);
        Ob2server ob2server2 = new Ob2server2(objectFor3D);
        objectFor3D.setMsg("20140420的3D号码是：127" );
        objectFor3D.setMsg("20140421的3D号码是：333" );
    }
}
