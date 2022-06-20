package com.example.hql.strategy;

/**
 * 类描述
 *
 * @author abel.huang@zkteco.com
 * @date 2022-05-05 17:34
 * @since 1.0.0
 */
public class DefendTBS implements IDefendBehavior {


    @Override
    public void defend() {
        System.out.println("铁布衫");
    }
}
