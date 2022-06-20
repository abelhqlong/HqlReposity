package com.example.hql.strategy;

/**
 * 类描述
 *
 * @author abel.huang@zkteco.com
 * @date 2022-05-05 17:34
 * @since 1.0.0
 */
public class AttackJY implements IAttackBehavior {

    @Override
    public void attack() {
        System.out.println("九阳神功！");
    }
}
