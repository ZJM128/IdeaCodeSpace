package day13.review;

@Table("t_user")
public class User {
    @Column(name = "no",type="int")
    private String id;
    @Column(name = "username",type="varchar2(20)")
    private String userName;
    @Column(name = "pwd",type = "char(6)")
    private String password;
    @Column(name = "email",type="varchar(50)")
    private String email;

    public User() {
    }

    public User(String id, String userName, String password, String email) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
