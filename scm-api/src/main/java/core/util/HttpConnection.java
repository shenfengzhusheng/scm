package core.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * http请求工具包
 * 
 * @project: web
 * @author:神风逐胜
 * @corporation:福建分享网络科技有限公司
 * @datetime:2016年2月17日
 */
public class HttpConnection {
    @SuppressWarnings("unused")
    private URL url;
    @SuppressWarnings("unused")
    private String charset;

    public HttpConnection(URL url) {
        this.url = url;
    }

    public HttpConnection(URL url, String charset) {
        this.url = url;
        this.charset = charset;
    }

    public static class A {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


    }

    public static void toJson() {
        // BdArea areacode=new BdArea();
        // areacode.setAreaCode("1");
        // areacode.setAreaName("测试");
        //
        // BdArea area2=new BdArea();
        // area2.setAreaCode("2");
        // area2.setAreaName("测试");
        // BdArea area3=new BdArea();
        // area3.setAreaCode("3");
        // area3.setAreaName("测试");
        // List<BdArea>list=new ArrayList<>();
        // list.add(areacode);
        // list.add(area2);
        //
        // list.add(area3);
        // Json json=new Json();
        // json.setSuccess(true);
        // json.setMsg("成功");
        // A a=new A();
        // a.setList(list);
        // a.setName("ccc");
        // json.setObj(a);
        // Grid grid=new Grid();
        // grid.setRows(list);
        // grid.setTotal(3L);
        // System.out.println(JSON.toJSON(grid));

    }

    public static void main(String[] args) throws Exception {
        String url = "";
        String method = "POST";
        Integer verification = 0;
        String contentType = "application/xml";
        String charset = "UTF-8";
        String content = "";
        System.out.println(doSend(url, content, method, verification, contentType, charset));

        // toJson();

    }

    @SuppressWarnings({})
    public static String doSend(String url, String content, String method, Integer verification, String contentType, String charset) {
        StringBuffer buf = new StringBuffer();
        HttpURLConnection conn = null;
        OutputStream out = null;
        BufferedReader in = null;
        try {
            // conn.setConnectTimeout(GlobalContant.HTTP_CONNECTTIMEOUT);
            // conn.setReadTimeout(GlobalContant.HTTP_READTIMEOUT);

            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setConnectTimeout(18000);
            conn.setReadTimeout(18000);
            byte[] b = content.getBytes("UTF-8");
            if (verification == 1) {// 需要校验
                String userName = "admin";
                String password = "admin";
                String input = userName + ":" + password; // 用户名以及登录密码
                if (input != null && input.trim().length() > 0) {
                    @SuppressWarnings("restriction")
					String encoding = new sun.misc.BASE64Encoder().encode(input.getBytes());
                    conn.setRequestProperty("Authorization", "Basic   " + encoding); // 设置用户名，用户密码
                }

            }
            conn.setRequestProperty("Content-Type", contentType);

            conn.setRequestProperty("Content-Length", String.valueOf(b.length));
            conn.setRequestMethod(method);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            out = conn.getOutputStream();
            out.write(b);
            out.flush();
            out.close();

            InputStreamReader isr = null;
            if (charset != null) {
                isr = new InputStreamReader(conn.getInputStream(), charset);
                // System.out.println("response encoding is:"+isr.getEncoding());
            } else {
                isr = new InputStreamReader(conn.getInputStream(), "UTF-8");
            }
            in = new BufferedReader(isr);
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                buf.append(inputLine);
            }

        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
            if (conn != null) {
                conn.disconnect();
            }
        }

        return buf.toString();
    }


    public static String httpRequest(String url, String content) {
        HttpURLConnection connection = null;
        PrintWriter out = null;
        StringBuffer buf = new StringBuffer();

        try {

            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            out = new PrintWriter(connection.getOutputStream());
            out.print(content);
            out.flush();
            out.close();
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            while ((line = bufReader.readLine()) != null) {
                // System.out.println(line);
                buf.append(line);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return buf.toString();
    }
}
