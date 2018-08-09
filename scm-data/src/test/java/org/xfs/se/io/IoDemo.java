package org.xfs.se.io;

import org.springframework.util.Base64Utils;

import java.io.*;

public class IoDemo {

    public static void main(String[]args)throws Exception{
        FileInputStream fis = new FileInputStream("D:\\test.txt");

        int len = 0;
        byte[] buf = new byte[1024];
        while((len=fis.read(buf))!=-1){
            String content=new String(buf,0,len);
            byte[]bytes=content.getBytes("utf-8");

             System.out.println(new String(Base64Utils.decode(bytes),"utf-8"));
        }
        fis.close();
    }

    public static void out()throws Exception{
        File file=new File("E:\\work\\resources\\resource.txt");
        FileInputStream fis = new FileInputStream(file);


        FileWriter writer=new FileWriter("D:\\test.txt");
        int len = 0;
        byte[] buf = new byte[1024];
        while((len=fis.read(buf))!=-1){
            String content=new String(buf,0,len);
            writer.write(Base64Utils.encodeToString(content.getBytes("utf-8")));
        }

        writer.close();
        fis.close();
    }
}
