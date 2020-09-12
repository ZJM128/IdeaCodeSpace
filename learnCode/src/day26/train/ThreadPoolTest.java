package day26.train;

import java.util.concurrent.*;

public class ThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        ThreadPoolExecutor service= (ThreadPoolExecutor) executorService;
        service.setMaximumPoolSize(20);

        service.execute(new EvenSum());
        SumNumber sumNumber =new SumNumber();
       // OddSum oddSum = new OddSum();
        Future submit = service.submit(sumNumber);
        try {
            System.out.println(submit.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // 关闭线程池
        service.shutdown();


    }

}
class EvenSum implements Runnable{
    @Override
    public void run() {
        int sum=0;
        for (int i = 2; i <= 100; i+=2) {
            sum+=i;
        }
        System.out.println("偶数的总和为:"+sum);
    }
}

