package day07;

public class morningTest01 {
    public static void main(String[]args){
        User user=new User();
        user.setUid("123");
        user.setName("jom");
        user.setPassword("1256");
        System.out.println(user.getInfo());
    }
}
