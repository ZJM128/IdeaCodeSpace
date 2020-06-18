package day17;

import org.junit.Test;

import java.util.*;

public class moriningExer {
  /*  public static void main(String[] args) {
        List list=new ArrayList();
        list.add("12");
        list.add("16");
        list.add("15");
        list.add("14");
        list.add("13");

        for (Object o : list) {
            System.out.println(o);
        }
    }*/
  @Test
  public void test02(){
      List<String> list=new ArrayList<>();
      list.add("adf");
      list.add("As");
      list.add("DRfppfg");
      list.add("DRfog");
      list.add("DRfjjfg");

      for (String o : list) {
          System.out.println(o.toUpperCase());
      }

      List list1=new LinkedList();
  }
  @Test
  public void test01(){
      Set set=new HashSet();
      Car Car=new Car("李白",123);
      Car Car1=new Car("李白",123);
      Car Car2=new Car("白居易",1213);
      Car Car3=new Car("白居易",1213);
      Car Car4=new Car("李贺",11123);
      set.add(Car);
      set.add(Car2);
      set.add(Car3);
      set.add(Car4);
      set.add(Car1);
      for (Object o : set) {
          System.out.println(o);
      }
  }
}
class Car{
    private String name;
    private double price;

    public Car(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Double.compare(car.price, price) == 0 &&
                Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}