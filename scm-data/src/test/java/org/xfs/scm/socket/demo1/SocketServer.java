package org.xfs.scm.socket.demo1;




import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer {
    private static int port=55555;
    private static Map<String,Socket> clients=new HashMap<String,Socket>();
    public static void main(String[]args)throws Exception{
        threadPoolServer();

    }
    public static void customtThreadPoolServer()throws Exception{
        ServerSocket serverSocket=new ServerSocket(port);
        // server将一直等待连接的到来
        System.out.println("server将一直等待连接的到来");
        //如果使用多线程，那就需要线程池，防止并发过高时创建过多线程耗尽资源
        ExecutorService threadPool= Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        while(true){
            Socket socket=serverSocket.accept();
            String client=UUID.randomUUID().toString();
            clients.put(client,socket);
            int count=1;
            Runnable runnable=()->{
                try{
                    // 建立好连接后，从socket中获取输入流，并建立缓冲区进行读取
                    InputStream inputStream=socket.getInputStream();
                    OutputStream outputStream=socket.getOutputStream();

                    byte[]bytes;

                    while(true){
                        int first=inputStream.read();
                        //如果读取的值为-1 说明到了流的末尾，Socket已经被关闭了，此时将不能再去读取
                        if(first==-1){
                            break;
                        }
                        int second=inputStream.read();
                        int length =(first<<8)+second;
                        // 然后构造一个指定长的byte数组
                        bytes=new byte[length];
                        // 然后读取指定长度的消息即可
                        inputStream.read(bytes);
                        System.out.println(Thread.currentThread().getName()+"get message from client: " + new String(bytes, "UTF-8"));
                        Thread.sleep(new Random().nextInt(5)*500);

                        String message=Thread.currentThread().getName()+"server recived["+client+"] message!now is:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                        byte[]sendBytes=message.getBytes("UTF-8");
                        outputStream.write(sendBytes.length>>8);
                        outputStream.write(sendBytes);
                        outputStream.flush();
                    }
                    inputStream.close();
                //   socket.close();
                }catch (Exception e){
                    e.printStackTrace();
                }

            };
            threadPool.submit(runnable);
//            if(clients.size()>1){
//                String key=clients.keySet().toArray()[clients.keySet().size()-1].toString();
//                System.out.println("--------------clients ["+key+"]");
//                Socket socket2=clients.get(key);
//                OutputStream outputStream=socket.getOutputStream();
//                String content="socket:"+Thread.currentThread().getName()+":send to ["+key+"]message!";
//                outputStream.write(content.getBytes("UTF-8"));
//                outputStream.flush();
//                //      Thread.sleep(new Random().nextInt(5)*50);
//                //    outputStream.close();
//            }
         }

    }
    public static void threadPoolServer()throws Exception{
        ServerSocket serverSocket=new ServerSocket(port);
        // server将一直等待连接的到来
        System.out.println("server将一直等待连接的到来");
        //如果使用多线程，那就需要线程池，防止并发过高时创建过多线程耗尽资源
        ExecutorService threadPool= Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        while(true){
            Socket socket=serverSocket.accept();
            Runnable runnable=()->{
              try{
                  // 建立好连接后，从socket中获取输入流，并建立缓冲区进行读取
                  InputStream inputStream=socket.getInputStream();
                  byte[]bytes=new byte[1024];
                  int len;
                  StringBuilder sb = new StringBuilder();

                  while ((len = inputStream.read(bytes)) != -1) {
                      // 注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
                      sb.append(new String(bytes, 0, len, "UTF-8"));
                  }
                  System.out.println(Thread.currentThread().getName()+"get message from client: " + sb);
                  OutputStream outputStream=socket.getOutputStream();
                  outputStream.write((Thread.currentThread().getName()+":reciver message!").getBytes("UTF-8"));
                  Thread.sleep(new Random().nextInt(5)*50);
                  outputStream.close();
                  inputStream.close();
                  socket.close();
              }catch (Exception e){
                  e.printStackTrace();
              }

            };
            threadPool.submit(runnable);

        }

    }
    public static void simple4(){
        try {
            ServerSocket server=new ServerSocket(port);
            // server将一直等待连接的到来
            System.out.println("server将一直等待连接的到来");

            while(true){
                Socket socket=server.accept();
                // 建立好连接后，从socket中获取输入流，并建立缓冲区进行读取
                InputStream inputStream=socket.getInputStream();
                byte[]bytes=new byte[1024];
                int len;
                StringBuffer stringBuffer=new StringBuffer();
                while((len=inputStream.read(bytes))!=-1){
                    // 注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
                    stringBuffer.append(new String(bytes,0,len,"UTF-8"));
                }
                System.out.println("get message from client: " + stringBuffer);
                inputStream.close();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void simple3(){
        try {
            ServerSocket serverSocket=new ServerSocket(port);
            // server将一直等待连接的到来
            System.out.println("server将一直等待连接的到来");
            Socket socket=serverSocket.accept();
            // 建立好连接后，从socket中获取输入流，并建立缓冲区进行读取
            InputStream inputStream=socket.getInputStream();
            byte[]bytes;
            // 因为可以复用Socket且能判断长度，所以可以一个Socket用到底
            while(true){
                // 首先读取两个字节表示的长度
                int first=inputStream.read();
                //如果读取的值为-1 说明到了流的末尾，Socket已经被关闭了，此时将不能再去读取
                if(first==-1){
                    break;
                }
                int second=inputStream.read();
                int length =(first<<8)+second;
                System.out.println("lenth is:"+length);
                // 然后构造一个指定长的byte数组
                bytes=new byte[length];
                // 然后读取指定长度的消息即可
                inputStream.read(bytes);
                System.out.println("get message from client: " + new String(bytes, "UTF-8"));

            }
            inputStream.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void simple2(){
        try{
            ServerSocket server=new ServerSocket(port);
            // server将一直等待连接的到来
            System.out.println("server将一直等待连接的到来");
            Socket socket=server.accept();
            // 建立好连接后，从socket中获取输入流，并建立缓冲区进行读取
            InputStream inputStream=socket.getInputStream();
            byte[]bytes=new byte[1024];
            int len;
            StringBuffer stringBuffer=new StringBuffer();
            //只有当客户端关闭它的输出流的时候，服务端才能取得结尾的-1
            while((len=inputStream.read(bytes))!=-1){
                // 注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
                stringBuffer.append(new String(bytes,0,len,"UTF-8"));
            }
            System.out.println("get message from client: " + stringBuffer);

            OutputStream outputStream=socket.getOutputStream();
            outputStream.write("Hello Client,I get the message.".getBytes("UTF-8"));
            inputStream.close();
            outputStream.close();
            socket.close();
            server.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void simple(){

        try {
            // 监听指定的端口

            ServerSocket serverSocket=new ServerSocket(port);
            // server将一直等待连接的到来
            System.out.println("server将一直等待连接的到来");
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
            System.out.println("get message from client: " + stringBuilder);
            inputStream.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
