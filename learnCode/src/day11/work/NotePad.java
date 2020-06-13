package day11.work;

public class NotePad extends Computer {
    private boolean canChange;

    public boolean isCanChange() {
        return canChange;
    }

    public void setCanChange(boolean canChange) {
        this.canChange = canChange;
    }

    public NotePad(String cpu, String memory, String disk, double price, boolean canChange) {
        super(cpu, memory, disk, price);
        this.canChange = canChange;
    }

    public NotePad(String cpu, String memory, String disk, boolean canChange) {
        super(cpu, memory, disk);
        this.canChange = canChange;
    }
    public String getDetail(){
        return super.getDetail()+" "+(canChange?"是":"不是");
    }

    public void NoDetail(){
        System.out.println("no");
    }
}
