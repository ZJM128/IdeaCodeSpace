package day14.review;

public class test19 {

    /**
     * String和其他的包装类一样都是不可变的,一旦值改变了 都会创建新的对象
     * @param tIn
     * @param intIn
     * @param integer
     * @param string
     */
    public static  void f1(TEXT tIn,int intIn,Integer integer,String string){
         tIn.num=200;
         tIn.name="bcd";
         intIn=200;
         integer=50;
         string="bcd";
    }

    public static void main(String[] args) {
        TEXT t=new TEXT(100,"abc");
        int num=100;
        Integer integer=100;
        String str="abc";
        f1(t,num,integer,str);
        System.out.println(t.num+" "+t.name+" "+num+" "+integer+" "+str);
    }
}
class TEXT{
    public int num;
    public String name;

    public TEXT(int num, String name) {
        this.num = num;
        this.name = name;
    }
}