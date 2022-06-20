package com.example.hql.factory;

/**
 * 类描述
 *
 * @author abel.huang@zkteco.com
 * @date 2022-04-28 14:37
 * @since 1.0.0
 */
public class RoujiaMoStore {
    
    private SimpleRouJiaMoFactroy factroy;
    
    public RoujiaMoStore(SimpleRouJiaMoFactroy factroy)
    {
        this.factroy = factroy;
    }

    public RouJiaMo sellRouJiaMo(String type) {
        RouJiaMo rouJiaMo = factroy.createRouJiaMo(type);
        System.out.println("正在处理....."+rouJiaMo.name);
        rouJiaMo.prepare();
        rouJiaMo.fire();
        rouJiaMo.pack();
        return rouJiaMo;
    }
    

//    public RouJiaMo sellRouJiaMo(String type) {
//        RouJiaMo rouJiaMo = null;
//
//        if (type.equals("Suan")) {
//            rouJiaMo = new SuanRouJiaMo();
//
//        } else if (type.equals("Tian")) {
//            rouJiaMo = new TianRouJiaMo();
//        } else if (type.equals("La")) {
//            rouJiaMo = new LaRouJiaMo();
//        }
//        return rouJiaMo;
//    }
}
