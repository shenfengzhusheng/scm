package org.xfs.jvm;

public class JvmDemo {

    public static  void main(String[]args){
        //-Xms5m -Xmx20m -XX:+PrintGCDetails -XX:+UseSerialGC -XX:+PrintCommandLineFlags

        System.out.println("max memoery:"+Runtime.getRuntime().maxMemory());
        System.out.println("free memory:"+Runtime.getRuntime().freeMemory());
        System.out.println("total memmory:"+Runtime.getRuntime().totalMemory());

        byte[]b1=new byte[1*1024*1024];
        System.out.println("分配了1M内存！");
        System.out.println("max memoery:"+Runtime.getRuntime().maxMemory());
        System.out.println("free memory:"+Runtime.getRuntime().freeMemory());
        System.out.print("total memmory:"+Runtime.getRuntime().totalMemory());
        byte[] b2 = new byte[4*1024*1024];
        System.out.println("分配了4M");

        System.out.println("max memoery:"+Runtime.getRuntime().maxMemory());
        System.out.println("free memory:"+Runtime.getRuntime().freeMemory());
        System.out.print("total memmory:"+Runtime.getRuntime().totalMemory());
    }
}
