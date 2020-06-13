package day11.work;

public class Computer {

    private String cpu;
    private String memory;
    private String disk;
    private double price;


    public Computer() {
    }

    public Computer(String cpu, String memory, String disk) {
        this.cpu = cpu;
        this.memory = memory;
        this.disk = disk;
    }

    public Computer(String cpu, String memory, String disk, double price) {
        this.cpu = cpu;
        this.memory = memory;
        this.disk = disk;
        this.price = price;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getDisk() {
        return disk;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDisk(String disk) {
        this.disk = disk;
    }

    public String getDetail(){
        return "cpu"+cpu+" memory"+memory+" disk"+disk+" price"+price;
    }
}
