package day11.work;

public class Designer extends Programmer{
    private double  bonus;

    public Designer() {
    }

    public Designer(String id, String name, int age, double salary, double bonus) {
        super(id, name, age, salary);
        this.bonus = bonus;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public String getInfo(){
        return super.getInfo()+" "+bonus;
    }
}
