package org.xfs.test.thread;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 神风逐胜 on 2017/10/1 0001.10:43
 * version:1.0
 */
public class WatchThread {
    private String name= UUID.randomUUID().toString();


    public void testThread()throws InterruptedException{
        int threadNum=10;
        // 初始化countDown
        CountDownLatch latch=new CountDownLatch(threadNum);
        //此处不可以用接口 需要使用Executor的实现类 ExecutorService  Executor未提供shutdown等方法
        ExecutorService executor=Executors.newFixedThreadPool(threadNum);

        for(int i=0;i<threadNum;i++){
          Runnable task=new TestThread(latch);
            // 执行
            executor.execute(task);
        }
        latch.await(); // 等待所有子线程执行完
        //固定线程池执行完成后 将释放掉资源 退出主进程
        executor.shutdown();//并不是终止线程的运行，而是禁止在这个Executor中添加新的任务
        // do work end
        //退出主进程
        System.out.println(Thread.currentThread().getName() + "+++++++结束.");
    }
    public static void main(String[]args)throws Exception{
        WatchThread test=new WatchThread();
        test.testThread();
    }
    class TestThread implements Runnable{
        private CountDownLatch threadSignal;
        public TestThread(CountDownLatch threadSignal){
            this.threadSignal=threadSignal;
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "开始..." + name);
            System.out.println("开始了线程：" + threadSignal.getCount());
            // do shomething
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //核心处理逻辑

            //  用到成员变量name作为参数
            threadSignal.countDown();
            System.out.println(Thread.currentThread().getName()+"结束，还有"+threadSignal.getCount()+"个线程");
       }
    }
}
