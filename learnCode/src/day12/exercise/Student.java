package day12.exercise;

public class Student implements Sortable {

    private double score;

    public Student() {
    }

    public Student(double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public int compare(Sortable s) {
        if(s==this){
            return 0;
        }
        if(s instanceof Student){
            if(((Student) s).score==this.score){
                return 0;
            }else if(((Student) s).score<this.score){
                return 1;
            }else{
                return -1;
            }
        }
        return -2;
    }

    @Override
    public String toString() {
        return "Student{" +
                "score=" + score +
                '}';
    }
}
