package day09;

public class PC extends Computer {
    boolean box;

    public boolean getBox() {
        return box;
    }

    public void setBox(boolean box) {
        this.box = box;
    }

    public String getDetail(){
         String detail = super.getDetail();
         return "我是pc:"+detail+" "+(box?"有主机":"没有主机");
    }

    public PC(boolean box, String CPU,String memory,String disk,int price) {
        super(CPU,memory,disk,price);
        this.box = box;

    }


    public PC() {}


}
