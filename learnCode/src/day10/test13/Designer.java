package day10.test13;

public class Designer extends Programmer{
    private double award;

    public double getAward() {
        return award;
    }

    public void setAward(double award) {
        this.award = award;
    }

    public Designer() {
    }

    public Designer(String id, String name, int age, double salary, double award) {
        super(id, name, age, salary);
        this.award = award;
    }

    public String getInfo(){
        return super.getInfo()+"\t"+award;
    }
}
