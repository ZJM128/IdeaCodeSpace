package day12;

/**
 * 声明Clothing接口 ，在接口中声明 calcArea方法、getColor方法和getDetails方法。
 * 改写Shirt类实现Clothing接口，然后实现接口中的所有方法。
 * 在TestShirt类的main方法中：
 * 试着创建 Clothing对象，确认是否允许？
 * 使用本态引用创建Shirt对象，并调用calcArea方法，打印计算结果。
 * 使用Clothing多态引用创建Shirt对象，并调用calcArea方法，打印计算结果。
 */
public interface Clothing {
     double calcArea();
    String getColor();
    String getDetails();

}
