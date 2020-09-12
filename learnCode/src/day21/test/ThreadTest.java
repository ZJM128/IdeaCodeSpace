package day21.test;

public class ThreadTest {

    public static void main(String[] args) {
        // 创建线程对象
        myThread thread=new myThread();
        // 启动线程
        thread.start();
        //
        for(int i=1;i<=100;i++){
            if(i%2!=0){
                System.out.println("main主线程 奇数:"+i);
            }
        }
    }
}
class  myThread extends Thread{

    @Override
    public void run(){
        for(int i=1;i<=100;i++){
            if(i%2==0){
                System.out.println("子线程 偶数"+i);
            }
        }
    }

}