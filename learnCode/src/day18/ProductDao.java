package day18;

import java.util.List;

public interface ProductDao {
    void add(Product p);
    void edit(int index,Product product);
    Product get(int index);
    void delete(int index);
    List<Product> getAll();
    int getSize();
}
