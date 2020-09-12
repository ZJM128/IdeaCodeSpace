package day26.train;

/*
*@Description:例题：生产者、消费者问题：
 * 生产者(Productor)将产品交给店员(Clerk)，而消费者(Customer)从店员处取走产品，
 * 店员一次只能持有固定数量的产品(比如:20），如果生产者试图生产更多的产品，店员会叫生产者停一下，
 * 如果店中有空位放产品了再通知生产者继续生产；如果店中没有产品了，店员会告诉消费者等一下，如果店中
 * 有产品了再通知消费者来取走产品。
 * 分析： 线程  操作 资源类
 * 1. 是否是一个多线程问题？是！生产者 、消费者
 * 2. 是否有线程安全问题呢？有！
 * 2. 是否有共享数据？有！产品数量
 * 2. 是否需要使用同步机制？是！
 * 3. 是否涉及到线程通信？是！
*@author:zhijm
*@Date:2020/6/26 14:43
*/
public class ConsumerProducerTest {
    public static void main(String[] args) {
        Clerk clerk=new Clerk();
        new Product("店员",clerk).start();
        new Customer("消费者1",clerk).start();
        new Customer("消费者2",clerk).start();

    }
}
class Product extends Thread{
    private Clerk clerk;

    public Product(String name, Clerk clerk) {
        super(name);
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println("开始生产");
       while(true){
          /* try {
               Thread.sleep(500);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }*/
           clerk.productClerk();
       }
    }
}
class Customer extends  Thread{
    private Clerk clerk;

    public Customer(String name, Clerk clerk) {
        super(name);
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println("开始消费");
        while (true){
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.customerClerk();
        }
    }
}
class Clerk{
    private int number=0;
    /**
     * 生成
     */
    public synchronized void productClerk(){
        if(number<20){

            number++;
            System.out.println(Thread.currentThread().getName()+"生成了第:"+number);
            // 生成一个就唤醒吃货
            notifyAll();
        }else{
            // 如果大于了20个就等待
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 消费
     */
    public synchronized void customerClerk(){
        if(number>0){

            System.out.println(Thread.currentThread().getName()+"吃了第:"+number);
            number--;
            notifyAll();
        }else{
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
