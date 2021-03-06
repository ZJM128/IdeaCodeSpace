package day15.structures.com.atguigu.team.domiain;

public class Architect extends Designer{
    private int stock;


    public Architect(int id, String name, int age, double sayaly, double bonis, int stock,Equipment equipment) {
        super(id, name, age, sayaly,bonis,equipment);
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
       return   getId()+"\t"+getName()+"\t"+getAge()+"\t"+getSayaly()+"\t\t"+positionName.ARCHITECT.getName()+"\t"+getStatus()+"\t"+getBonis()+"\t"+stock+"\t"+getEquipment().getDescription();

    }
}
