package day21.homeWork;

/*
*@Description: 一个工人，在工作，生产玩具，每500毫秒就生产一个玩具，当生产到第20个玩具时，就吃三个馒头，每1000毫秒吃一个
  吃完后继续工作
*@author:zhijm
*@Date:2020/6/21 18:31
*/
public class Test02 {
    public static void main(String[] args) {

        EatBread bread=new EatBread();
        Thread thread=new Thread(bread);
        thread.start();

        int count=0;
        while(true){

            count++;
            System.out.println("生成玩具:"+count+"个");
            if(count==20){
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
class EatBread implements Runnable{
    @Override
    public void run() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i=0;i<3;i++){
            System.out.println("吃馒头");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}