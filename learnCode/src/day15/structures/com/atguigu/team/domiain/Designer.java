package day15.structures.com.atguigu.team.domiain;

public class Designer extends Programmer {
    private double bonis;

    public Designer() {
    }

    public Designer(int id, String name, int age, double sayaly,  double bonis,Equipment equipment) {
        super(id, name, age, sayaly, equipment);
        this.bonis = bonis;
    }

    public double getBonis() {
        return bonis;
    }

    public void setBonis(double bonis) {
        this.bonis = bonis;
    }

    @Override
    public String toString() {
        return   getId()+"\t"+getName()+"\t"+getAge()+"\t"+getSayaly()+"\t\t"+positionName.DESIGNER.getName()+"\t"+getStatus()+"\t"+bonis+"\t\t\t"+getEquipment().getDescription();
    }
}
