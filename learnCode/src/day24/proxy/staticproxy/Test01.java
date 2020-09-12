package day24.proxy.staticproxy;

public class Test01 {
    public static void main(String[] args) {
        // 被代理的对象
        ClothFactory clothFactory=new NickClothFactory();
        // 代理对象
        ClothFactory clothFactory1=new ProxyClothFactory(clothFactory);
        // 代理对象开始工作
        clothFactory1.productCloth();
    }
}
