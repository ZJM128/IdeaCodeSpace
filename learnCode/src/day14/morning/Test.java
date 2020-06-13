package day14.morning;

/**
 * java的包装类中：Byte，Short，Integer，Long，Character使用static代码块进行初始化缓存，
 * 其中Integer的最大值可以通过java.lang.Integer.IntegerCache.high设置；
 * Boolean使用static final实例化的对象；
 * Float和Double直接new的对象没有使用缓存
 */
public class Test {

    @org.junit.Test
    public void test01(){
        State state=State.PLAYING;
       // System.out.println(state);
        State[] values = State.values();
        for (State value : values) {
            printState(value);
        }
/*
        State stop = State.valueOf("STOP");
        System.out.println(stop);*/


    }

    public static void printState(State state){
        switch(state){
            case PLAYING:
                System.out.println("播放中");
                break;
            case PREPAREING:
                System.out.println("准备中");
                break;
            case SUSPEND:
                System.out.println("暂停");
                break;
            case STOP:
                System.out.println("停止");
                break;
            default:
                System.out.println("出故障了");
                break;
        }
    }
    @org.junit.Test
    public void test02(){
        Byte b=127;
        Byte b2=127;
        System.out.println(b==b2);
        Short s=-128;
        Short s1=-128;
        System.out.println(s==s1);
        System.out.println(s.equals(s1));
        String str=" ";
        System.out.println(str.isEmpty());

    }
    @org.junit.Test
    public void test03(){
        String str1 = "ab";
        String str2=new String("cd");

        String str3="abcd";
        String str4="cd";
        String str5=str1+str4;
        String str6="ab"+"cd";
        System.out.println(str3==str5);
        System.out.println(str3==str6);

       // System.out.println(str1==str2);
    }


}
interface Person01{
    void say();
}