package org.xfs.test.thread;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by 神风逐胜 on 2017/10/1 0001.11:06
 * version:1.0
 */
public class TaskTest {
    static class Sum implements Callable<Long>{
        private final long from;
        private final long to;

        Sum(long from,long to){
            this.from=from;
            this.to=to;
        }
        @Override
        public Long call() throws Exception {
            long acc=0;
            for(long i=from;i<=to;i++){
                acc+=i;
            }
            return acc;
        }
    }

    public static void main(String[]args)throws Exception{
        int threadNum=3;
        ExecutorService executorService= Executors.newFixedThreadPool(threadNum);
        List<Future<Long>> results=executorService.invokeAll( Arrays.asList(
                new Sum(0,10),new Sum(100,1000),new Sum(10000,1000*1000*10)
        ));;
        executorService.shutdown();;
        for (Future<Long>result:results){
            System.out.println(result.get());
        }
    }
}
