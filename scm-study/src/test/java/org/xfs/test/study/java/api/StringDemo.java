package org.xfs.test.study.java.api;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 神风逐胜 on 2017/9/24 0024.10:50
 * version:1.0
 */
public class StringDemo {
    public static void main(String[]args){

    }
    public static void format(){
        String nowTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
        System.out.println("now is :"+nowTime);

		int seq=1;
		//填充订单号
		String orderNo=String.format("%010d",1);
		System.out.println("orderNo is :"+orderNo);

       // String orderNo=
    }
}
