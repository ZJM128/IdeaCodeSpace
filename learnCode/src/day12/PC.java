package day12;

public class PC extends Equipment {
    private boolean box;

    public PC() {
    }

    public PC(String name, String color, boolean box) {
        super(name, color);
        this.box = box;
    }

    public boolean isBox() {
        return box;
    }

    public void setBox(boolean box) {
        this.box = box;
    }


}
