package day22.test;

public class WaitAndNotify {
    public static void main(String[] args) {
        Store store=new Store();
        customer customer=new customer(store);
        Thread thread=new Thread(store);
        Thread thread1=new Thread(customer);
        thread.start();
        thread1.start();
    }
}
class Store implements Runnable{
    private int count =0;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public void run() {
        while(true){
            synchronized (this) {
                if (count == 0) {
                    count++;
                    System.out.println("店铺生产一个馒头");
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

class customer implements Runnable{
    private Store store;
    public customer(Store store) {
        this.store=store;
    }
    @Override
    public void run() {

        while (true){
            synchronized (store){
                if(store.getCount()==1){
                    store.setCount(0);
                    System.out.println("顾客吃一个馒头");
                    store.notify();
                }
            }
        }
    }
}