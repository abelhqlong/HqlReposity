package com.example.hql.adapter;

/**
 * 类描述
 *
 * @author abel.huang@zkteco.com
 * @date 2022-05-07 10:22
 * @since 1.0.0
 */
public class Test {
    public static void main(String[] args) {
        Mobile mobile = new Mobile();
        V5Power v5Power = new V5PowerAdapter(new V220Power());
        mobile.inputPower(v5Power);
    }
}
