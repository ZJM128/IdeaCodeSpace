package day21.homeWork;
/*
*@Description:可以做一个倒计时10秒钟，提示新年快乐
*@author:zhijm
*@Date:2020/6/21 18:31
*/
public class test01 {
    public static void main(String[] args) {
        myThread thread=new myThread();
        thread.start();
    }
}
class myThread extends Thread{
    @Override
    public void run() {

        for(int i=1;i<=10;i++){
            System.out.println(i);
            if(i==10){
                System.out.println("新年快乐");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}