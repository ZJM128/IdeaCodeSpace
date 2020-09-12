package day22.mroning;
/*
线程可以嵌套使用
*@Description:一个工人，在工作，生产玩具，每500毫秒就生产一个玩具，当生产到第20个玩具时，就吃三个馒头，每1000毫秒吃一个
  吃完后继续工作
*@author:zhijm
*@Date:2020/6/22 12:38
*/
public class Test02 {
    public static void main(String[] args) {
        Worker worker=new Worker();
        worker.start();
    }
}
class Worker extends Thread{
    @Override
    public void run() {
       for(int i=1;i<=200;i++){
           System.out.println("生产第"+i+"个玩具");
           if(i%20==0){
               EatBread bread=new EatBread();
               bread.start();
               try {
                   bread.join();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               // 吃馒头
           }
       }
    }
}

class EatBread extends Thread{
    @Override
    public void run() {
       for(int i=0;i<3;i++){
           System.out.println("吃第"+(i+1)+"个馒头");
       }
    }
}