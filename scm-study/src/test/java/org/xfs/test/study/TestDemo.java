package org.xfs.test.study;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


public class TestDemo {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // String str = "{\"status\":\"Success\",\"message\":\"SH15YDD20170705000793\",\"data\":\"\"}";
        String str =
                "[{\"gpsTime\":\"2017-07-06 09:45:12\",\"hum\":\"\",\"interfaceType\":\"JY\",\"lat\":\"31.08455\",\"lon\":\"121.504133\",\"placename\":\"上海市闵行区浦江镇浦星公路\",\"point\":\"\",\"speed\":\"0\",\"status\":0,\"temp1\":\"-19.5\",\"temp2\":\"12.5\",\"temp3\":\"\",\"temp4\":\"\",\"vehicleId\":\"11601\",\"vehicleNo\":\"沪D82375\"}]";
        //
        if (str.startsWith("[{")) {
            JSONArray arry = JSONObject.parseArray(str);
            System.out.println(arry);

        } else {
            JSONObject json = JSONObject.parseObject(str);
            System.out.println(json);
        }
        Long l1=3L;
        Long l2=3L;
        Integer a=128;
        Integer b=128;
        System.out.println(l1==l2);
        System.out.println(a==b);



        // String status = json.getString("status");
        // if (status != null && "Error".equals(status)) {
        // System.out.println("失败！");
        // } else if (status != null && "Sucess".equals(status)) {
        // System.out.println("成功！");
        // } else {
        // System.out.println("失败！");
        //
        // }
    }

}
