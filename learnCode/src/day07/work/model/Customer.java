package day07.work.model;

public class Customer {
    private String name;
    private char gender;
    private int age;
    private String phone;
    private String email;

    public void setName(String name){
        this.name=name;
    }

    public void setGender(char gender){
        this.gender=gender;
    }

    public void setAge(int age){
        this.age=age;
    }
    public void setPhone(String phone){
        this.phone=phone;
    }
    public void setEmail(String email){
        this.email=email;
    }



    public String getName(){
        return name;
    }
    public char getGender(){
        return gender;
    }

    public int getAge(){
        return age;
    }

    public String getPhone(){

        return phone;
    }

    public String getEmail(){
        return email;
    }
    public Customer(){}

    public Customer(String name){
        this.name=name;
    }

    public Customer(String name,char gender){
        this(name);
        this.gender=gender;
    }

    public Customer(String name,char gender,int age){
        this(name,gender);
        this.age=age;
    }

    public Customer(String name,char gender,int age,String phone){
        this(name,gender,age);
        this.phone=phone;
    }

    public Customer(String name,char gender,int age,String phone,String email){
        this(name,gender,age,phone);
        this.email=email;
    }

}
