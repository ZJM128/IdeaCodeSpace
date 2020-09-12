package day22.reviewtest;

public class ThreadToWaitAndNotify {
    public static void main(String[] args) {
        ThreadToWait threadToWait = new ThreadToWait();
        threadToWait.start();

        for (int i = 0; i < 100; i++) {
            System.out.println("main:" + i);
            if (i % 10 == 0) {
                synchronized (threadToWait.getClass()) {
                    threadToWait.getClass().notify();
                }
            }
        }
    }
}

class ThreadToWait extends Thread {
    @Override
    public void run() {

        for (int i = 0; i < 100; i++) {
            System.out.println("子线程:" + i);
            synchronized (this.getClass()) {
                if (i == 5) {
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