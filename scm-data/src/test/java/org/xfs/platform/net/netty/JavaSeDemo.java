package org.xfs.platform.net.netty;

public class JavaSeDemo {

    public static void main(String []args){
        byte[]bytes=int2ByteArray(0X1243162D);
        for(byte b:bytes){
            System.out.println("b:"+b);
        }
    }
    public static byte[]int2ByteArray(int i){
        byte[]result=new byte[4];
        result[0]=(byte)((i>>24)&0xFF);
        result[1] = (byte) ((i >> 16) & 0xFF);
        result[2] = (byte) ((i >> 8) & 0xFF);
        result[3] = (byte) (i & 0xFF);
        return result;
    }
}
