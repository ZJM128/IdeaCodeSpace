package day24.proxy.staticproxy;
/**
 * 代理类
 */

/**
 * 代理的工厂 负责对被代理对象的处理结果进行收尾或者进一步处理结果
 * 静态代理编译前已经知道了需要代理的类型是谁了(ClothFactory)
 */
public class ProxyClothFactory implements ClothFactory{

    private  ClothFactory clothFactory;

    public ProxyClothFactory(ClothFactory clothFactory) {
        this.clothFactory = clothFactory;
    }

    @Override
    public void productCloth() {
        System.out.println("Nick工厂已经准备好了~~~~");
        clothFactory.productCloth();
        System.out.println("生成完毕,开始售货~~~~~~");
    }
}
