package day06;

public class Test01 {
    public static void main(String []args){
        Boy boy=new Boy();
        Girl girl=new Girl();

        boy.setName("李晨");
        boy.setAge(36);

        girl.setName("范冰冰");
        boy.marry(girl);
        girl.marry(boy);
    }
}
