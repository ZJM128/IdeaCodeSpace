package day23.test;

public class Student extends Person {
    public int studentId;
    private String school;
    int height;
    protected String parent;

    public Student() {
    }

    private Student(int studentId) {
        this.studentId = studentId;
    }

    public Student(int studentId, String school) {
        this.studentId = studentId;
        this.school = school;
    }

    private void eat(){
        System.out.println("hello eat()");
    }
    public void eat(String name){
        System.out.println(name+":吃饭");
    }
    String eat(int age){
        return age+"";
    }
    protected void eat(double money){
        System.out.println(money);
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", school='" + school + '\'' +
                ", height=" + height +
                ", parent='" + parent + '\'' +
                '}';
    }
}
