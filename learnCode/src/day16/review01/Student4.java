package day16.review01;

public class Student4 implements Comparable<Student4> {
    private String name;
    private int age;
    private double score;

    public Student4(String name, int age, double score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student10{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                '}';
    }

    @Override
    public int compareTo(Student4 o) {
        if(this.score>o.score){
            return -1;
        }else if(this.score<o.score){
            return 1;
        }
        return this.age-o.age;
    }


}
