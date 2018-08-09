<%--
  Created by IntelliJ IDEA.
  User: fengling9874
  Date: 2018/3/2 0002
  Time: 9:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="application/x-msdownload" pageEncoding="UTF-8" %>

<%@ page import="java.io.OutputStream" %>
<%@ page import="java.io.FileInputStream" %>
<%@ page import="java.net.URLEncoder" %>

<html>
<head>
    <title>apk下载</title>
</head>
<%--<script type="text/javascript">--%>
    <%--window.location.href="/static/apk/企货通项目需求清单.xlsx";--%>
<%--</script>--%>
<body>

 <%
     response.setContentType("application/x-download");
     String filedownload = "d://qhtshipper-release.apk";
     String filedisplay = "货主apk";
     filedisplay = URLEncoder.encode(filedisplay,"UTF-8");
     response.addHeader("Content-Disposition","attachment;filename=" + filedisplay);

     OutputStream outp = null;
     FileInputStream in = null;
     try
     {
         outp = response.getOutputStream();
         in = new FileInputStream(filedownload);

         byte[] b = new byte[1024];
         int i = 0;

         while((i = in.read(b)) > 0)
         {
             outp.write(b, 0, i);
         }
         outp.flush();
     }
     catch(Exception e)
     {
         System.out.println("Error!");
         e.printStackTrace();
     }
     finally
     {
         if(in != null)
         {
             in.close();
             in = null;
         }
         if(outp != null)
         {
             outp.close();
             outp = null;
         }
     }
 %>

</body>
</html>




