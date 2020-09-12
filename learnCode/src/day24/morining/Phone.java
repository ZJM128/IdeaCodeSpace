package day24.morining;

public class Phone {
    private String name;
    private String brand;
    private double price;

    public Phone(String name, String brand, double price) {
        this.name = name;
        this.brand = brand;
        this.price = price;
    }
    private String say(){
        return "我是:"+name+"我的品牌是:"+brand+"我的售价是:"+price;
    }
}
