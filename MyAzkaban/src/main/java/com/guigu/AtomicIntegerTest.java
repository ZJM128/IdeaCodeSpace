package com.guigu;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest implements Runnable{
    private static AtomicInteger count=new AtomicInteger(0);
    public static void main(String[] args) {
        for(int i=0;i<3;i++){
            new Thread(new AtomicIntegerTest()).start();
        }
    }

    @Override
    public void run() {
        synchronized (AtomicIntegerTest.class) {
            System.out.println(count.get());
            final int i = count.incrementAndGet();
        }
    }
}
