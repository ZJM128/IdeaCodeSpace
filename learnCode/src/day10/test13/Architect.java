package day10.test13;

public class Architect extends Designer {
    private double count;

    public Architect(String id, String name, int age, double salary,  double award, double count) {
        super(id, name, age, salary, award);
        this.count = count;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public String getInfo(){
        return super.getInfo()+"\t"+count;
    }
}
