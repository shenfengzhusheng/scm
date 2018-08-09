package core.util.http;

import core.util.string.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.StringTokenizer;

/**
 * Created by yl1371 on 2017/4/11.
 */
public class RequestInfoUtil {
    private static final Logger logger = LoggerFactory.getLogger(RequestInfoUtil.class);

    /**
     * 错误日志
     * 
     * @param request
     */
    public static String getRequestInfo(HttpServletRequest request) {
        StringBuffer buf = new StringBuffer();
        buf.append("request_url=").append(request.getRequestURL()).append("\r\n").append("local_port=").append(request.getLocalPort())
                .append("\r\n").append("method=").append(request.getMethod()).append("\r\n").append("remote_address=")
                .append(getIpAddr(request)).append("\r\n").append("RemoteAddr=").append(request.getRemoteAddr()).append("\r\n")
                .append("macAddr=").append(getMACAddress(getIpAddr(request))).append("\r\n")
                // .append("request body:").append(getRequestBody(request)).append("\r\n")
                .append(getRequestHeaders(request)).append("\r\n").append(getRequestParameters(request));
        return buf.toString();
    }

    private static StringBuffer getRequestHeaders(HttpServletRequest request) {
        Enumeration<String> headers = request.getHeaderNames();
        StringBuffer headerBuf = new StringBuffer();
        headerBuf.append("[header]:\r\n");
        while (headers.hasMoreElements()) {
            String headerName = headers.nextElement();
            headerBuf.append(headerName).append(":").append(request.getHeader(headerName)).append("\r\n");
            if (headerName.equalsIgnoreCase("authorization")) {
                headerBuf.append(getAuthorizationInfo(request.getHeader(headerName).trim()));
            }
        }
        return headerBuf;
    }


    private static StringBuffer getRequestParameters(HttpServletRequest request) {
        StringBuffer headerBuf = new StringBuffer("[param]:\r\n");
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            headerBuf.append(paramName).append(":").append(request.getParameter(paramName)).append("\r\n");
        }
        return headerBuf;
    }

    public static StringBuffer getAuthorizationInfo(String authorization) {
        if (!StringUtil.isEmpty(authorization)) {
            StringBuffer authBuf = new StringBuffer();
            StringTokenizer st = new StringTokenizer(authorization);
            if (st.hasMoreTokens()) {
                String type = st.nextToken();
                if ("basic".equalsIgnoreCase(type)) {
                    if (st.hasMoreTokens()) {
                        // String auth = st.nextToken();
                        try {
                            // byte[] ignore = Base64Util.decode(auth.toCharArray(), 0, auth.length());
                            // String enc = "UTF-8";
                            // String str = new String(ignore, enc);
                            // int col = str.indexOf(58);
                            // authBuf.append("name:").append(str.substring(0, col)).append("\r\n")
                            // .append("password:").append(str.substring(col + 1)).append("\r\n");
                            return authBuf;
                        } catch (Throwable var9) {
                            ;
                        }
                    }
                }
            }

        }
        return null;

    }

    /**
     * 通过HttpServletRequest返回IP地址
     * 
     * @param request HttpServletRequest
     * @return ip String
     * @throws Exception
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        try {
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } catch (Exception e) {
            logger.error("getIpAddr error!");
        }
        return ip;
    }

    /**
     * 通过IP地址获取MAC地址
     * 
     * @param ip String,127.0.0.1格式
     * @return mac String
     * @throws Exception
     */
    public static String getMACAddress(String ip) {
        String line = "";
        String macAddress = "";
        final String MAC_ADDRESS_PREFIX = "MAC Address = ";
        final String LOOPBACK_ADDRESS = "127.0.0.1";
        try {
            // 如果为127.0.0.1,则获取本地MAC地址。
            if (LOOPBACK_ADDRESS.equals(ip)) {
                InetAddress inetAddress = InetAddress.getLocalHost();
                // 貌似此方法需要JDK1.6。
                byte[] mac = NetworkInterface.getByInetAddress(inetAddress).getHardwareAddress();
                // 下面代码是把mac地址拼装成String
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < mac.length; i++) {
                    if (i != 0) {
                        sb.append("-");
                    }
                    // mac[i] & 0xFF 是为了把byte转化为正整数
                    String s = Integer.toHexString(mac[i] & 0xFF);
                    sb.append(s.length() == 1 ? 0 + s : s);
                }
                // 把字符串所有小写字母改为大写成为正规的mac地址并返回
                macAddress = sb.toString().trim().toUpperCase();
                return macAddress;
            }
            // 获取非本地IP的MAC地址

            Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);
            InputStreamReader isr = new InputStreamReader(p.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                if (line != null) {
                    int index = line.indexOf(MAC_ADDRESS_PREFIX);
                    if (index != -1) {
                        macAddress = line.substring(index + MAC_ADDRESS_PREFIX.length()).trim().toUpperCase();
                    }
                }
            }
            br.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return macAddress;
    }

    public static String getRequestBody(HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF-8");
            int size = request.getContentLength();
            if (size > 0) {
                InputStream is = request.getInputStream();

                byte[] reqBodyBytes = readBytes(is, size);
                // 获取请求body中的内容
                return new String(reqBodyBytes);
            }

        } catch (Exception e) {
            logger.info("get request body error!");
        }

        return null;
    }

    public static byte[] readBytes(InputStream is, int contentLen) {
        if (contentLen > 0) {
            int readLen = 0;
            int readLengthThisTime = 0;
            byte[] message = new byte[contentLen];
            try {
                while (readLen != contentLen) {
                    readLengthThisTime = is.read(message, readLen, contentLen - readLen);
                    if (readLengthThisTime == -1) {// Should not happen.
                        break;
                    }
                    readLen += readLengthThisTime;
                }
                return message;
            } catch (IOException e) {
                // Ignore
                e.printStackTrace();
            }
        }

        return new byte[] {};
    }
}
