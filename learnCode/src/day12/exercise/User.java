package day12.exercise;

public class User {
    private String userType;
    private int Id;

    public String getUserType() {
        return userType;
    }

    public User(int id) {
        Id = id;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
    public String toString(){
        return Id+"-"+userType;
    }
}
