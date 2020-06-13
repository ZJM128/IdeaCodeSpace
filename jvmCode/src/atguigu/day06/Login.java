package atguigu.day06;

public class Login {
    public static void main(String[] args) {
            Login login=new Login();
            User user=login.login("1","2");

        System.out.println(user.getDetail());

    }

    public User login(String userName,String passWord) {
        User user = new User();
        if ("1".equals(userName) && "2".equals(passWord)) {
            user.ID = 1;
            user.userName = userName;
            user.passWord = passWord;
        }
        return user;
    }

}
