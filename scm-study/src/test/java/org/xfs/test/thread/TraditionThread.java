package org.xfs.test.thread;

/**
 * Created by 神风逐胜 on 2017/10/10 0010.21:00
 * version:1.0
 */
public class TraditionThread {
    public static void main(String[]args){
        final Business business=new Business();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<50;i++){
                    business.sub(i);
                }
            }
        }).start();

        for(int i=0;i<100;i++){
            business.main(i);
        }
    }

    static class Business{
        private boolean isSub=true;
        public synchronized void sub(int seq){
            while(!isSub){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for(int i=0;i<10;i++){
                System.out.println("sub thread sequence of,"+seq+" loof of"+i);
            }
            isSub=false;
            this.notify();
        }

        public synchronized void main(int seq){
            while(isSub){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for(int i=0;i<100;i++){
                System.out.println("main thread sequence of,"+seq+" loof of"+i);
            }
            isSub=true;

            this.notify();

        }
    }
}
