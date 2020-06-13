package day09;

import org.junit.Test;
/*
*@Description:①父类的私有属性子类是不能直接操作的,可以通过get或者set方法操作
*             ②子类可以继承父类的公共方法,但是子类也可以重写该方法
*              调用的时候采用就近原则,如果子类有就调用子类的方法,如果子类没有
*              就调用父类的
*              ③方法的重写(override)的规则:Ⅰ方法名 参数列表必须一样
*                                         Ⅱ 控制修饰符不能比父类的方法权限低
*                                        Ⅲ 返回值类型 一样或者是分类方法返回值的子类
*
*@author:zhijm
*@Date:2020/6/3 18:05
*/
public class PersonTest {
    @Test
    public void test01(){
        Student student = new Student();
        student.setAge(26);
        System.out.println(student.getAge());
        student.eat();
    }
}
