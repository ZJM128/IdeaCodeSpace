package day11.work;

public class Single {
    private Single(){}

    private static Single onlyOne=new Single();

    public static Single getSingle(){
        return onlyOne;
    }
}
class TestSingle{
    public static void main(String[] args) {
        Single single=Single.getSingle();
        Single single1=Single.getSingle();

        if(single==single1){
            System.out.println("相同的");
        }
    }
}