package day22.test;

public class WaitAadNotify {
    public static void main(String[] args) {
        SteamedStore steamedStore=new SteamedStore();
        steamedStore.start();
        while (true){
            synchronized (steamedStore.getClass()) {
                if(SteamedStore.getCount()==1) {
                    SteamedStore.setCount(0);
                    System.out.println("顾客吃一个馒头");
                    steamedStore.getClass().notify();
                }
            }
        }
    }
}
class SteamedStore extends Thread{
    private static  int count=0;

    public static void setCount(int count) {
        SteamedStore.count = count;
    }

    public static int getCount() {
        return count;
    }

    @Override
    public void run(){
        while (true){
            synchronized (this.getClass()) {
                if(count==0) {
                    count++;
                    System.out.println("店铺生产一个馒头");
                    try {
                        this.getClass().wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}

