package org.xfs.test.study.java.api.socket;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    private static int port=55533;

    public static void main(String[]args)throws Exception{
        ServerSocket serverSocket=new ServerSocket(port);

        //server 将一直将等待连接
        System.out.println("server将一直等待连接的到来");

        while(true){
            Socket socket=serverSocket.accept();
            // 建立好连接后，从socket中获取输入流，并建立缓冲区进行读取
            InputStream inputStream=socket.getInputStream();
            byte[]bytes=new byte[1024];
            int len;
            StringBuilder stringBuilder=new StringBuilder();
            while((len=inputStream.read(bytes))!=-1){
                //注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8

                stringBuilder.append(new String(bytes,0,len,"UTF-8"));
            }

            System.out.println("get message from client:"+stringBuilder);
            inputStream.close();
            socket.close();
            serverSocket.close();
        }

    }
}
