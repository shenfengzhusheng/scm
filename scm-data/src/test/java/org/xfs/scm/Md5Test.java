package org.xfs.scm;

import org.xfs.scm.common.utils.date.DateUtil;
import org.xfs.scm.common.utils.encrypt.MD5Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Md5Test {

    public static void main(String[]args){
        System.out.println(MD5Util.md5("376584"));
        try {
            //1517195252000
           System.out.println( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2018-01-29 11:07:32").getTime());

           System.out.println(DateUtil.dateToString(new Date(1517195252000L)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
