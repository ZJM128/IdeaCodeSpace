package day06;

/**
 * public 公共的 任何地方都可方法
 * private 私用的 只有在本类中才能访问
 */
class PersonTest{
    public static void main(String []args){
        Person person=new Person(13);
        //person.setAge(-1);
        System.out.println("年龄:"+ person.getAge());
    }
}

class Person {
    private int age=10;
    public Person(int age){
        System.out.println(this.age);
        this.age=age;
    }
    public void setAge(int age){
        if(age<0 || age>200){
            System.out.println("妖怪呀");
        }else{
            this.age=age;
        }
    }

    public int getAge(){
        return this.age;
    }
}
