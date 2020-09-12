package day21.homeWork;

/*
*@Description:一个工人，在工作，生产玩具，每500毫秒就生产一个玩具，当生产到第20个玩具时，就吃三个馒头，每1000毫秒吃一个
  吃完后继续工作
*@author:zhijm
*@Date:2020/6/22 10:51
*/
public class Test02_1 {
    public static void main(String[] args) {
        Worker01 worker01=new Worker01();
        worker01.start();
    }
}
class Worker01 extends Thread{
    @Override
    public void run() {
        int count=1;
       for(int i=0;i<200;i++){
           System.out.println("生产第"+count+"个玩具");
           if(count%20==0){
               EatBread01 eatBread=new EatBread01();
               eatBread.start();
               try {
                   eatBread.join();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
           count++;
       }
    }
}
class EatBread01 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("吃馒头");
        }
    }
}