package com.example.hql.strategy;

/**
 * 类描述
 *
 * @author abel.huang@zkteco.com
 * @date 2022-05-05 16:40
 * @since 1.0.0
 */
public class RoleC extends Role {
    public RoleC(String name)
    {
        this.name = name;
    }
    @Override
    protected void display() {
        System.out.println("样子3");
    }

    @Override
    protected void run() {
        System.out.println("烟雾弹");

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
