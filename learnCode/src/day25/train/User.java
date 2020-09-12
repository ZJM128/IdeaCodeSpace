package day25.train;
@Table("t_User")
@Info(info = "你好")
public class User {
    @Column(name = "no",type = "int")
    private int id;
    @Column(name = "name",type = "varChar(20)")
    private String userName;
    @Column(name = "email",type = "varChar(20)")
    private String email;



}
