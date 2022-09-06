package com.example.hql.factory;

/**
 * 类描述
 *
 * @author abel.huang@zkteco.com
 * @date 2022-04-28 14:53
 * @since 1.0.0
 */
public class Test {
    public static void main(String[] args) {
        RoujiaMoStore roujiaMoStore = new RoujiaMoStore(new SimpleRouJiaMoFactroy());
        roujiaMoStore.sellRouJiaMo("Suan");
    }
}
