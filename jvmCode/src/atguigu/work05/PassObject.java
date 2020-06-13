package atguigu.work05;

public class PassObject {
    public static void main(String[] args) {
        PassObject passObject=new PassObject();
        Circle c=new Circle();
        passObject.printAreas(c,5);
    }
    public void printAreas(Circle c,int times){
        System.out.println("Radius\t\tArea");
       for(int i=1;i<=times;i++){
           c.radius=i;
           double result=c.radius;
           System.out.print(result+"\t\t\t");
           System.out.println(c.findArea());
       }
       c.radius+=1;
        System.out.println("now radius is :"+c.radius);
    }
}
