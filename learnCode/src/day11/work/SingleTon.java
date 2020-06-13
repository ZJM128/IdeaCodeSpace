package day11.work;

public class SingleTon {
    private SingleTon(){}

    private  static SingleTon singleTon=null;
    public static SingleTon getInstance(){
        if(singleTon==null){
            singleTon=new SingleTon();
        }
        return singleTon;
    }
}
class SingleTonTest{
    public static void main(String[] args) {
        SingleTon singleTon=SingleTon.getInstance();
        SingleTon singleTon1=SingleTon.getInstance();
        if(singleTon==singleTon1){
            System.out.println("相同");
        }
    }
}