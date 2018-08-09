package org.xfs.scm.platform.util.fastdfs;



import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

import java.io.*;

/**
 * FastDFS Java客户端工具类
 */
public class FileManager implements FileManagerConfig {

    private static final long serialVersionUID = 1L;
    private static TrackerClient trackerClient;
    private static TrackerServer trackerServer;
    private static StorageServer storageServer;
    private static StorageClient storageClient;
    static {
        try {
       //     String classPath = "D:"
           // String fdfsClientConfigFilePath = "d:/fdfs_client.conf";
           // ClientGlobal.init(fdfsClientConfigFilePath);
            ClientGlobal.initByTrackers("121.42.205.204:22122");
            trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();

            storageClient = new StorageClient(trackerServer, storageServer);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <strong>方法概要： 文件上传</strong> <br>
     * <strong>创建时间： 2016-9-26 上午10:26:11</strong> <br>
     *
     * @param file
     *            file
     * @return fileAbsolutePath
     * @author Wang Liang
     */
    public static String upload(FastDFSFile file,NameValuePair[] valuePairs) {
        String[] uploadResults = null;
        try {
            uploadResults = storageClient.upload_file(file.getContent(),file.getExt(), valuePairs);
            String groupName = uploadResults[0];
            String remoteFileName = uploadResults[1];

            String fileAbsolutePath = PROTOCOL
                    + TRACKER_NGNIX_ADDR
                    //+ trackerServer.getInetSocketAddress().getHostName()
                    //+ SEPARATOR + TRACKER_NGNIX_PORT
                    + SEPARATOR + groupName
                    + SEPARATOR + remoteFileName;
            return fileAbsolutePath;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static boolean delete(String groupName,String filePath){
        try {
            int i = storageClient.delete_file(groupName,filePath);
            if(i==1){
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }

        return false;
    }

    @SuppressWarnings("resource")
	public static void main(String[]args)throws  Exception{
       // FileReader fileReader=new FileReader("d:/1.jpg");
        File img=new File("C:\\Users\\Administrator\\Pictures\\timeline-grid\\BFXv83aCQAAaZdZ.jpg");
        FileInputStream fileInputStream=new FileInputStream(img);
        byte[] bytes =new byte[(int)img.length()];
        fileInputStream.read(bytes);
        FastDFSFile file =new FastDFSFile(bytes,"jpg");
        NameValuePair[] meta_list = new NameValuePair[4];
        meta_list[0] = new NameValuePair("fileName", "123");
        meta_list[1] = new NameValuePair("fileLength", img.length()+"");
        meta_list[2] = new NameValuePair("fileExt", "jpg");
        meta_list[3] = new NameValuePair("fileAuthor", "Jeken.Liu");
        String filePath = FileManager.upload(file,meta_list);
        System.out.println("result:"+filePath);
    }
    public static String getFileExtName(String fileName) {
        if(fileName.lastIndexOf(".") > 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        return "";
    }
}