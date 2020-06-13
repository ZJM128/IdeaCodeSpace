package day07;

public class User {

    private String uid;
    private String name;
    private String password;

    public User(){}

    public User(String uid){
        this.uid=uid;
    }

    public User(String uid,String password){
        this(uid);
        this.password=password;
    }

    public User(String uid,String password,String name){
        this(uid,password);
        this.name=name;

    }
    public void setUid(String uid){
        this.uid=uid;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setPassword(String password){
        this.password=password;
    }

    private String getUid(){
        return uid;
    }
    private String getName(){
        return name;
    }

    private String getPassword(){
        return password;
    }

    public String getInfo(){
        return "ID号码:"+uid+" 姓名:"+name+" ,密码:"+password;
    }

}
