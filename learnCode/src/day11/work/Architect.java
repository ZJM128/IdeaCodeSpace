package day11.work;

public class Architect extends Designer{
    private  double stock;

    public Architect() {
    }

    public Architect(String id, String name, int age, double salary ,double bonus, double stock) {
        super(id, name, age, salary,  bonus);
        this.stock = stock;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public String getInfo(){
        return super.getInfo()+" "+stock;
    }
}
