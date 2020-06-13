package day12;

public class NotePad extends Equipment {
    private boolean canChange;

    public NotePad() {

    }

    public NotePad(String name, String color, boolean canChange) {
        super(name, color);
        this.canChange = canChange;
    }

    public boolean isCanChange() {
        return canChange;
    }

    public void setCanChange(boolean canChange) {
        this.canChange = canChange;
    }
}
