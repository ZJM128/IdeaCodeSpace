package day24.proxy.staticproxy;

/**
 * 被代理类
 */
public class NickClothFactory  implements ClothFactory{
    @Override
    public void productCloth() {
        System.out.println("生成一批优质的Nick衣服");
    }
}
