package com.example.hql.strategy;

/**
 * 类描述
 *
 * @author abel.huang@zkteco.com
 * @date 2022-05-05 17:30
 * @since 1.0.0
 */
public class Test {
    public static void main(String[] args) {
        RoleA roleA = new RoleA("小马");
        roleA.setAttackBehavior(new AttackJY())
                .setDefendBehavior(new DefendTBS());
//                .setDisplayBehavior(new DisplayA())
//                .setRunBehavior(new RunJCTQ());
        System.out.println(roleA.name + ":");
        roleA.run();
        roleA.attack();
        roleA.defend();
        roleA.display();   
    }
}
