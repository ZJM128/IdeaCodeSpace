package day10.warkbooks;
/*
*@Description:多态参数
*@author:zhijm
*@Date:2020/6/5 19:15
*/
public class PolymorphismTest04 {

    public static void main(String[] args) {
        Man man=new Man();
        show(man);
        Woman woman=new Woman();
        show(woman);
    }
    public static void show(Person person){
        person.sleep();
        person.eat();
        if(person instanceof Man){
            Man man=(Man)person;
            man.smoking();
        }

        if(person instanceof Woman){
            Woman woman=(Woman)person;
            woman.shopping();
        }

    }
}
