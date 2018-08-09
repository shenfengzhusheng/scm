package org.xfs.test.thread;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.*;

/**
 * Created by 神风逐胜 on 2017/10/1 0001.19:35
 * version:1.0
 */
public class Fibonacci extends RecursiveTask<BigInteger> {
    int n;
    public Fibonacci(){}
    Fibonacci(int i){
        this.n=i;
    }

    /**
     * 任务足够小,直接查表返回
     * @param small
     * @return
     */
    private BigInteger compute(int small){
        final int[]results={1,1,2,3,5,8,13,21,34,55,89};
        return new BigInteger(results[small]+"");
    }

    //实际业务计算
    @Override
    protected BigInteger compute() {
        if(n<10){
            return compute(n);
        }
        System.out.println(Thread.currentThread().getName());
        Fibonacci f1=new Fibonacci(n-1);//创建子任务
        Fibonacci f2=new Fibonacci(n-2);//创建子任务
        f2.fork();

        return f1.compute().add(f2.join());//两个子任务结果的合并
    }

    public static void main(String[]args){
        /*线程的数量有限,超过一定的数量会报错,内存溢出*/
        ForkJoinPool joinPool=new ForkJoinPool();
        Fibonacci fibonacci=new Fibonacci(50);//创建任务
        Future<BigInteger> result=joinPool.submit(fibonacci);
        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
