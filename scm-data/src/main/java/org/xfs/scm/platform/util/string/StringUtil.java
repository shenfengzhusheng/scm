package org.xfs.scm.platform.util.string;

/**
 * Created by 神风逐胜 on 2017/10/15 0015.15:27
 * version:1.0
 */
public class StringUtil {
    /**
     * 首字母小写
     * @param content
     * @return
     */
    public static String toLowerCaseFirstOne(String content){
        if(isEmpty(content))
            return null;
         StringBuilder builder=new StringBuilder();
         builder.append(content.substring(0,1).toLowerCase()).append(content.substring(1,content.length()));
        return builder.toString();
    }

    /**
     * 字符串是否为空或空白
     * @param str
     * @return
     */
    public static  boolean isEmpty(String str){
        if(str==null || "".equals(str)){
            return true;
        }
        return false;
    }

    /**
     * 字符中是否为空白
     * @param str
     * @return
     */
    public static boolean isBlank(String str){
        if(str ==null&&"".equals(str)){
            return true;
        }
        return false;

    }

    /**
     * 字符中是否为空白
     * @param str
     * @return
     */
    public static boolean isNotBlank(String str){
        if(str !=null&&!"".equals(str)){
            return true;
        }
        return false;

    }
    public static void main(String[]args){
        String str="";
        System.out.println(StringUtil.toLowerCaseFirstOne(str));
      //  char ch='a';
        System.out.println("ch is:"+str.toLowerCase());
    }
}
