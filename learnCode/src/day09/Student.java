package day09;

public class Student extends Person{
    private String studentId;
    public Student(){}

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    public void eat(){
        System.out.println("我是学生,我要吃好的");
    }
}
