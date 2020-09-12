package day22.homeWork;

public class SingleLazy {
}
class Single{
    private Single(){}

    private static Single single=null;

    public static Single getSingle(){
        if(single==null) {// 如果不加这个判断,每条线程都要在同步代码块处进行等待,这样效率低,所以需要再加一个判断,
            // 即使这里也会有多条线程,通过这个判断,但是不存在安全问题,因为同步代码块已有限制条件
            synchronized (Single.class) {
                if (single == null) {
                    // 此处会有线程安全
                    single = new Single();
                }
            }
        }
        return single;
    }

}