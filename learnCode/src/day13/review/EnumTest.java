package day13.review;

public class EnumTest {

    public static void main(String[] args) {
       /* Season1 spring=Season1.SPRING;
        System.out.println(spring);*/
     /* Season2 SPRING= Season2.SPRING;
        System.out.println(SPRING);*/

     /*Season3 season3=Season3.SPRING;
     switch (season3){
         case AUTUMN:
             System.out.println("秋天");
             break;
         case SPRING:
             System.out.println("春天");
             break;
         case SUMMER:
             System.out.println("夏天");
             break;
         case WINTER:
             System.out.println("冬天");
             break;
         default:
             System.out.println("没有相应的季节");
             break;
     }*/

     Season4 season4 =Season4.SPRING;
        season4.show();

    }
}
interface MyInterface{
    void show();
}