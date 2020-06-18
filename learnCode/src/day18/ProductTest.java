package day18;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ProductTest implements ProductDao {
    List<Product>list=new ArrayList<>();
    @Override
    public void add(Product p) {
        list.add(p);
    }

    @Override
    public void edit(int index,Product p) {
        list.set(index,p);
    }

    @Override
    public Product get(int index) {
        return list.get(index);
    }

    @Override
    public void delete(int index) {
        list.remove(index);
    }

    @Override
    public List <Product> getAll() {
        return list;
    }

    @Override
    public int getSize() {
        return list.size();
    }

    @Test
    public void test01(){
        ProductTest productTest=new ProductTest();
        Product product=new Product("12","苹果手机",123.5,"通讯产品");
        Product product1=new Product("13","华为手机",223.5,"通讯产品");
        Product product2=new Product("14","小米手机",323.5,"通讯产品");

        productTest.add(product);
        productTest.add(product1);
        productTest.add(product2);
        System.out.println(productTest.getAll());
        System.out.println(productTest.getSize());

    }
}
