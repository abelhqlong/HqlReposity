package com.example.hql.strategy;

/**
 * 类描述
 *
 * @author abel.huang@zkteco.com
 * @date 2022-05-05 16:40
 * @since 1.0.0
 */
public class RoleB extends Role {
    public RoleB(String name)
    {
        this.name = name;
    }
    @Override
    protected void display() {
        System.out.println("样子2");
    }

    @Override
    protected void run() {
        System.out.println("金蝉脱壳");

    }

    @Override
    protected void attack() {
        System.out.println("降龙十八掌");

    }

    @Override
    protected void defend() {
        System.out.println("铁头功");

    }
}
