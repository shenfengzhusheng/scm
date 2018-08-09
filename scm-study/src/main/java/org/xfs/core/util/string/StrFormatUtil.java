package org.xfs.core.util.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StrFormatUtil {

    /**
     * 空字符串转换
     * 
     * @param str
     * @param defStr 替换字符
     * @return
     */
    public static String empStr(String str, String defStr) {
        if (StringUtil.isEmpty(str))
            str = defStr;
        return str;
    }


    /**
     * 空转为空字符串
     * 
     * @param str
     * @return
     */
    public static String nullToTemp(String str) {
        if (null == str)
            return "";
        else
            return str;
    }


    /**
     * 去掉最后一个字符
     * 
     * @param cont
     * @return
     */
    public static String removeLast(String cont) {
        if (cont.length() > 1) {
            cont = cont.substring(0, cont.length() - 1);
        } else {
            cont = "";
        }
        return cont;
    }



    /**
     * 删除左边的空格
     */
    public static String trimLeft(String value) {
        if (value == null)
            return "";
        String result = value;
        char ch[] = result.toCharArray();
        int index = -1;
        for (int i = 0; i < ch.length; i++) {
            if (Character.isWhitespace(ch[i])) {
                index = i;
            } else {
                break;
            }
        }
        if (index != -1) {
            result = result.substring(index + 1);
        }
        return result;
    }


    /**
     * 删除右边的空格
     * 
     * @param value
     * @return
     */
    public static String trimRight(String value) {
        if (value == null)
            return "";
        String result = value;
        char ch[] = result.toCharArray();
        int endIndex = -1;
        for (int i = ch.length - 1; i > -1; i--) {
            if (Character.isWhitespace(ch[i])) {
                endIndex = i;
            } else {
                break;
            }
        }
        if (endIndex != -1) {
            result = result.substring(0, endIndex);
        }
        return result;
    }

    // ------------------字符串格式
    /**
     * 获得安全的可保存的字符串
     * 
     * @param str
     * @return
     */
    public static String getSafeString(String str) {
        String result = str;
        result = result.replace("{", "");
        result = result.replace("}", "");
        result = result.replace("[", "");
        result = result.replace("]", "");
        result = result.replace(",", "");
        result = result.replace("\"", "");
        result = result.replace("'", "");
        result = result.replace(":", "");
        result = result.replaceAll("\r\n", "\\\\r\\\\n");
        return result;
    }

    /**
     * 路径格式化 如果尾巴没路径符号补充路径符号
     * 
     * @param path
     * @return
     */
    public static String pathFormat(String path) {
        if (path == null)
            return null;
        if (!path.endsWith("/")) {
            path = path + "/";
        }
        return path;
    }

    /**
     * 字符串删除引号
     * 
     * @param cont
     * @return
     */
    public static String repYinHao(String cont) {
        String reStr = "";
        if (cont == null)
            return "";
        else {
            reStr = cont.replaceAll("'", "’");
        }
        return reStr;
    }


    /**
     * 去除字符串中的空行空格
     * 
     * @param str
     * @return
     */
    public static String replaceBlank(String str) {
        String after = str.replaceAll("\n|\r|\t|\\s*", "");
        return after;
    }


    /**
     * 移除所有的jason干扰字符{,},",[,]
     * 
     * @param str
     * @return
     */
    public static String replaceQ(String str) {
        str = str.replace("\"", "“");
        return str;
    }


    /**
     * 去掉所有html元素
     * 
     * @param str
     * @return
     */
    public static String removeHtml(String str) {
        if (str == null)
            return "";
        str = str.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "");
        str = str.replaceAll("[(/>)<]", "");
        return str.trim();
    }


    /**
     * 去掉所有html元素 去空格
     * 
     * @param str
     * @return
     */
    public static String removeHtmlTrim(String str) {
        if (str == null)
            return "";
        str = removeHtml(str);
        str = str.trim();
        return str;
    }

    /**
     * 去掉所有html元素 去空格 去除斜线 去除换行符号 简化字符内容
     * 
     * @param str
     * @return
     */
    public static String removeTagToSimple(String str) {
        str = removeHtmlTrim(str);
        str = replaceQ(str);
        str = replaceBlank(str);
        return str.trim();
    }



    /**
     * HTML 转 TEXT
     * 
     * @param inputString
     * @return
     */
    public static String htmlText(String inputString) {
        String htmlStr = inputString; // 含html标签的字符串
        String textStr = "";
        Pattern p_html;
        Matcher m_html;
        Pattern p_script;
        Matcher m_script;
        Pattern p_style;
        Matcher m_style;
        try {
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\s\S]*?<\/script>
                                                                                                     // }
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\s\S]*?<\/style>
                                                                                                  // }
            String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); // 过滤script标签

            p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll(""); // 过滤style标签

            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(""); // 过滤html标签

            textStr = htmlStr;

        } catch (Exception e) {
            System.err.println("Html2Text: " + e.getMessage());
        }

        return textStr;// 返回文本字符串
    }

    public static String htmlSimpText(String content) {
        if (content == null)
            return "";
        String html = content;
        html = html.replaceAll("'", "&apos;");
        html = html.replaceAll("\"", "&quot;");
        html = html.replaceAll("\t", "&nbsp;&nbsp;");// 替换跳格
        html = html.replaceAll(" ", "&nbsp;");// 替换空格
        html = html.replaceAll("<", "&lt;");
        html = html.replaceAll(">", "&gt;");
        html = html.replaceAll("\n", "<br/>");
        return html;
    }



    /**
     * 换行符号替换
     * 
     * @param str
     * @return
     */
    public static String spaceToBr(String str) {
        if (!StringUtil.isEmpty(str)) {
            str = str.replaceAll("\n\t", "<br/>");
            str = str.replaceAll("\r\n", "<br/>");
            str = str.replaceAll("\n", "<br/>");
            return str;
        } else {
            return "";
        }
    }

    /**
     * 半角转全角
     * 
     * @param input String.
     * @return 全角字符串.
     */
    public static String ToSBC(String input) {
        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == ' ') {
                c[i] = '\u3000';
            } else if (c[i] < '\177') {
                c[i] = (char) (c[i] + 65248);


            }
        }
        return new String(c);
    }

    /**
     * 全角转半角
     * 
     * @param input String.
     * @return 半角字符串
     */
    public static String ToDBC(String input) {
        if (!StringUtil.isEmpty(input)) {
            char c[] = input.toCharArray();
            for (int i = 0; i < c.length; i++) {
                if (c[i] == '\u3000') {
                    c[i] = ' ';
                } else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
                    c[i] = (char) (c[i] - 65248);


                }
            }
            String returnString = new String(c);
            return returnString;
        } else {
            return "";
        }
    }
}
