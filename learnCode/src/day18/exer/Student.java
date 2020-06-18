package day18.exer;

public class Student implements Comparable<Student>{
    private String name;
    private int ag;
    private double score;

    public Student(String name, int ag, double score) {
        this.name = name;
        this.ag = ag;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", ag=" + ag +
                ", score=" + score +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAg() {
        return ag;
    }

    public void setAg(int ag) {
        this.ag = ag;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public int compareTo(Student o) {
        if(this.score==o.score){
            return this.ag-o.ag;
        }else{
            return -(int)(this.score-o.score);
        }

    }
}
