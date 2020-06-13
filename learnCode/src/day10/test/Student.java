package day10.test;

public class Student extends Person {
    private String studentId;
    private int age;
    public Student() {
    }

    public Student(String studentId,int age,String name){
        super(name);// 必须写在第一行 ,目的:子类的属性和方法有些需要从父类中继承下来,所以需要先把父类进行初始化
                   // 子类才知道从父类继承了什么属性和方法,当然如果父类中没有无参数的构造器,子类一定要显示调用
                  // 父类的有参构造器,不然子类中会报错,因为子类构造器中必定存在super();
        //this(studentId); 此时this里面隐式调用了super() 父类的参数构造器,因而this和super不能共存
        this.studentId=studentId;
        this.age=age;
    }

    public Student(String studentId){
        this.studentId=studentId;
    }
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
   /* public String getDetail(){
        return "学号:"+studentId+" 名字:"+getName()+" 年龄:"+age;
    }*/

    public static void main(String[] args) {
        Person person = new Student("15",12,"张三");
        String detail = person.getDetail();
        System.out.println(detail);

    }
}
