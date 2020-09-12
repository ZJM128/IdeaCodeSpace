package day26.train;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {
    public static void main(String[] args) {
        SumNumber sumNumber=new SumNumber();
        FutureTask futureTask=new FutureTask(sumNumber);
        Thread thread=new Thread(futureTask);
        thread.start();

        try {
            System.out.println("一百以内偶数的和为:"+futureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
class SumNumber implements Callable{

    @Override
    public Object call() throws Exception {
        int sum=0;
        for (int i = 2; i <= 100; i+=2) {
            sum+=i;
        }
        return sum;
    }
}