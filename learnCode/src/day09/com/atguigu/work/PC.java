package day09.com.atguigu.work;

public class PC extends Computer {
    private String cpId;

    public String getCpId() {
        return cpId;
    }

    public void setCpId(String cpId) {
        this.cpId = cpId;
    }
    public String getDetails(){
        return cpId+" "+super.getDetails();
    }
}
