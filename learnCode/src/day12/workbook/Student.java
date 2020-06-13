package day12.workbook;

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
        int result = -2;
        if(s==this){
            return 0;
        }
        if(s instanceof Student){
            if(((Student) s).score==this.score){
                result=0;
            }else if(((Student) s).score<this.score){
                result=1;
            }else{
                result=-1;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "score=" + score +
                '}';
    }
}
