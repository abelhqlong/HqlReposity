package com.example.hql.adapter;

/**
 * 类描述
 *
 * @author abel.huang@zkteco.com
 * @date 2022-05-07 10:19
 * @since 1.0.0
 */
public class V5PowerAdapter implements V5Power {
    private V220Power v220Power;

    public V5PowerAdapter(V220Power v220Power) {
        this.v220Power = v220Power;
    }

    @Override
    public int provideV5Power() {
        int power = v220Power.provideV220Power();
        System.out.println("适配器：我悄悄的适配了电压。");
        return 5;
    }
}
