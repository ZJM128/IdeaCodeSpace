package day10.work;
/*
*@Description:顾客类
*@author:zhijm
*@Date:2020/6/4 16:54
*/
public class Customer {
    private String name;
    private char gender;
    private int age;
    private String phone;
    private String getPhone;


    public Customer() {
    }

    public Customer(String name, char gender, int age, String phone, String getPhone) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.phone = phone;
        this.getPhone = getPhone;
    }

    public String getName() {
        return name;
    }

    public char getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public String getGetPhone() {
        return getPhone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setGetPhone(String getPhone) {
        this.getPhone = getPhone;
    }
    public String getDetail(){
        return name+"\t\t"+gender+"\t\t"+age+"\t\t"+phone+"\t\t"+getPhone;
    }
}
