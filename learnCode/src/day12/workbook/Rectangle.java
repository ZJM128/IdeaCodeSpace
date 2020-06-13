package day12.workbook;

public class Rectangle implements Sortable{
    private int length;
    private int width;

    public Rectangle() {
    }

    public Rectangle(int length, int width) {
        this.length = length;
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int area(){
        return width*length;
    }
    @Override
    public int compare(Sortable s) {
        if(s==this){
            return 0;
        }
        if(s instanceof Rectangle){
            if(((Rectangle) s).area()==this.area()){
                return 0;
            }else if(((Rectangle) s).area()<this.area()){
                return 1;
            }else{
                return -1;
            }
        }else {
            return -2;
          //  throw new RuntimeException("不是同一个类型");
        }
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "length=" + length +
                ", width=" + width +
                '}';
    }
}
