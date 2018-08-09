package org.xfs.scm.common.utils.string;



/**
 * @Title: StringUtil.java
 * @Package cn.xfj.util
 * @Description:String工具类
 * @author: 神风逐胜
 * @mail:xixingyingzhongdui@gmail.com
 * @Date 2013-3-7上午9:09:03
 * @version
 */
public class StringUtil {

    public static void main(String[]args){
       System.out.println( fillZero(20,5));
    }
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
     * 格式化单号
     * @param seq
     * @param len
     * @return
     */
    public static String fillZero(int seq,int len){
        return String.format("%0"+len+"d",seq);
    }
    /**
     * 检查指定的字符串列表是否不为空。
     */
    public static boolean areNotEmpty(String... values) {
        boolean result = true;
        if (values == null || values.length == 0) {
            result = false;
        } else {
            for (String value : values) {
                result &= !isEmpty(value);
            }
        }
        return result;
    }
    public static boolean isBlank(String key){
        if(key==null)
            return false;
        if("".equals(key))
            return true;
        return false;
    }
    /**
     * 检查指定的字符串列表是否不为空。
     */
    public static boolean isEmpty(String value) {
        boolean result = true;
        if (value != null &&!"".equals(value)) {
            result = false;
        }
        return result;
    }
    public static boolean isNotBlank(String value){
        if(value == null ||"".equals(value)){
            return false;
        }
        return true;
    }
    /**
     * 格式化字符串
     * 
     * 例：formateString("xxx{0}bbb",1) = xxx1bbb
     * 
     * @param str
     * @param params
     * @return
     */
    public static String formateString(String str, String... params) {
        for (int i = 0; i < params.length; i++) {
            str = str.replace("{" + i + "}", params[i] == null ? "" : params[i]);
        }
        return str;
    }

    public static int indexOfIgnoreCase(final CharSequence str, final CharSequence searchStr) {
        return indexOfIgnoreCase(str, searchStr, 0);
    }
    public static int indexOfIgnoreCase(final CharSequence str, final CharSequence searchStr, int startPos) {
        if (str == null || searchStr == null) {
            return INDEX_NOT_FOUND;
        }
        if (startPos < 0) {
            startPos = 0;
        }
        final int endLimit = str.length() - searchStr.length() + 1;
        if (startPos > endLimit) {
            return INDEX_NOT_FOUND;
        }
        if (searchStr.length() == 0) {
            return startPos;
        }
        for (int i = startPos; i < endLimit; i++) {
            if (CharSequenceUtil.regionMatches(str, true, i, searchStr, 0, searchStr.length())) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    public static final int INDEX_NOT_FOUND = -1;

}
