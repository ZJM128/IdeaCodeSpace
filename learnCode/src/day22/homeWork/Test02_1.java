package day22.homeWork;

public class Test02_1 {
    public static void main(String[] args) {
        new Thread(new PrintOu1()).start();
        new Thread(new PrintJi1()).start();
    }
}
class PrintJi1 implements Runnable{
    @Override
    public void run() {
        int i=1;
        while(i<100){
            synchronized ("java") {//this ×  this.getClass ×  一个常量(字符串)
                for (int j = 0; j < 5; j++) {//打印就连续打印5个
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("奇数---："+i);
                    i=i+2;
                }
                //打印完成后，唤醒另外一个线程，然后自己等待
                "java".notify();//第一次执行，"java"没有等待的线程
                if(i<100){//因为我没有打印完，所以我等待，当i超过100了，说明我已经完成任务了，就结束就可以
                    try {
                        "java".wait();//执行完wait，将当前线程挂起并会释放锁资源，后面的代码是不执行的
                        //等待的代码在锁里面，锁没有运行完，另外一个线程可以进入到锁中？
                        //可以把奇偶写在一起，骚操作一把  可以尝试
                        //先等待还是先唤醒   先唤醒在等待
                        //唤醒可以放在循环前面吗，和放在循环后面有区别吗，我放在循环前面好像也是一样的效果
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    System.out.println("大于100:"+i);
                }
                System.out.println(" ");
            }
        }
    }
}

class PrintOu1 implements Runnable{
    @Override
    public void run() {
        int i=2;
        while(i<100){
            synchronized ("java") {
                for (int j = 0; j < 5; j++) {//打印就连续打印5个
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("偶数："+i);
                    i=i+2;
                }
                "java".notify();
                if(i<100){
                try {
                    "java".wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
               }
            }
        }
    }
}
