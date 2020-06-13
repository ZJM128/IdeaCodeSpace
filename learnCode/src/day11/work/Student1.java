package day11.work;

public class Student1 extends Person1 {
    private  String ID;
    private double score;

    public Student1() {
    }

    public Student1(String name, int age, String ID, double score) {
        super(name, age);
        this.ID = ID;
        this.score = score;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
    public void say(){
        super.say();
        System.out.print("ID"+ID+"score"+score);
    }
}
