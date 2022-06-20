package com.example.hql.adapter;

/**
 * 类描述
 *
 * @author abel.huang@zkteco.com
 * @date 2022-05-07 09:41
 * @since 1.0.0
 */
public class Mobile {
    
    public void inputPower(V5Power power) {
        int provideV5Power = power.provideV5Power();
        System.out.println("手机（客户端）：我需要5V电压充电，现在是-->" + provideV5Power + "V");
    }
}
