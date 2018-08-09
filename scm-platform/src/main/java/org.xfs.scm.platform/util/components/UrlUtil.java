package org.xfs.scm.platform.util.components;

public class UrlUtil {


    /**
     * @ 图片地址工具类：将(A:a;B:b)类型字符串拼接成 ("/" + a + "/"+b)返回
     * @param url
     * @return url
     * @author dk
     */
    public static String urlSplit(String url) {
        String[] urlarray, grouparray, patharray;
        String group, path;
        if (url == null || url == "") {
            return url;
        }
        //首先根据分号，分割成两个数组
        urlarray = url.split(";");
        if(urlarray != null && urlarray.length == 2){
            grouparray = urlarray[0].split(":");
            patharray = urlarray[1].split(":");

            group = grouparray[1];
            path = patharray[1];
            url = "/" + group + "/" + path;
        }
        return url;
    }
    
    public static void main(String []args) {
    	String url="group:group1;path:M00/00/00/eSrNzFpEreeAbsvrAAAIJyioOGI537.jpg";
    	System.out.println(urlSplit(url));
    }


}
