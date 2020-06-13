package day11;

public class morningTest {
    String title="";
    int value;

    public morningTest() {
        title+="world";
    }
    public morningTest(int value){
        this();
        this.value=value;
        title="Hello";

    }

    public static void main(String[] args) {
       morningTest morningTest=new morningTest(5);
       if(morningTest instanceof Object){
           System.out.println("true");
       }
    }
}
