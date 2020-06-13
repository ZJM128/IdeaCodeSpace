package day11.work;

public class PC extends Computer {
    private boolean box;

    public boolean isBox() {
        return box;
    }

    public void setBox(boolean box) {
        this.box = box;
    }

    public PC(String cpu, String memory, String disk, boolean box) {
        super(cpu, memory, disk);
        this.box = box;
    }

    public PC(String cpu, String memory, String disk, double price, boolean box) {
        super(cpu, memory, disk, price);
        this.box = box;
    }

    public void peDetail(){
        System.out.println("pc");
    }
    public String getDetail(){
        return super.getDetail()+ (box?"有":"没有");
    }
}
