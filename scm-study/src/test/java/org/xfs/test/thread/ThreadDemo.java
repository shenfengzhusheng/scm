package org.xfs.test.thread;

import java.util.concurrent.CountDownLatch;

public class ThreadDemo {

    public static void main(String[] args) {
        int size = 1000;
        CountDownLatch latch = new CountDownLatch(size);
        for (int i = 0; i < size; i++) {
            final int c = i;
            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        latch.countDown();
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    ThreadDemo.doSome(c);
                }
            }).start();

        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("------------------------------------------>");
    }

    public static String doSome(int key) {
        String result = "st";
        System.out.println(Thread.currentThread().getName() + "=====>key:" + key);
        return result + key;
    }

}
