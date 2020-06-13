package day10.test14;

public class Test14 {
    public static void main(String[] args) {
        Circle circle=new Circle(13);
        Rectangle rectangle=new Rectangle(5,6);
        Triangle triangle=new Triangle(4,2.5);
        Graphic[]graphics=new Graphic[3];
        graphics[0]=circle;
        graphics[1]=rectangle;
        graphics[2]=triangle;
        list(graphics);
        sort(graphics);
        System.out.println("---------------");
        list(graphics);

    }
    public static  void list(Graphic[]graphics){
        for (Graphic graphic : graphics) {
            System.out.println(graphic.getInfo());
        }
    }

    private static void sort(Graphic[]graphics){
        boolean flag=false;
        for(int i=0;i<graphics.length-1;i++){
            for (int j = 0; j < graphics.length-1-i; j++) {
                if(graphics[j].getArea()>graphics[j+1].getArea()){
                    Graphic temp=graphics[j];
                    graphics[j]=graphics[j+1];
                    graphics[j+1]=temp;
                   // graphics[j]=graphics[j+1]^graphics[j];
                    //graphics[j+1]=graphics[j]^graphics[j+1];
                    flag=true;
                }
                if(!flag){
                    break;
                }else {
                    flag=false;
                }
            }
        }
    }
}
