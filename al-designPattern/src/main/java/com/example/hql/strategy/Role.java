package com.example.hql.strategy;

/**
 * 类描述
 *
 * @author abel.huang@zkteco.com
 * @date 2022-05-05 16:39
 * @since 1.0.0
 */
public abstract class Role {
    protected String name;


    protected IDefendBehavior defendBehavior;
    protected IDisplayBehavior displayBehavior;
    //    protected IRunBehavior runBehavior;
    protected IAttackBehavior attackBehavior;

    public Role setDefendBehavior(IDefendBehavior defendBehavior) {
        this.defendBehavior = defendBehavior;
        return this;
    }

    public Role setDisplayBehavior(IDisplayBehavior displayBehavior) {
        this.displayBehavior = displayBehavior;
        return this;
    }

//    public Role setRunBehavior(IRunBehavior runBehavior)
//    {
//        this.runBehavior = runBehavior;
//        return this;
//    }

    public Role setAttackBehavior(IAttackBehavior attackBehavior) {
        this.attackBehavior = attackBehavior;
        return this;
    }

    protected void display() {
        System.out.println("****************");
    }

    protected void run() {
        System.out.println("****************");
    }


    protected void attack() {
        attackBehavior.attack();
    }


    protected void defend() {
        defendBehavior.defend();
    }
}
