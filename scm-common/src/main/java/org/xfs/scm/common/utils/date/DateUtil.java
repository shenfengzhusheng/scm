package org.xfs.scm.common.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static void main(String  ... args ){
		System.out.println(DateUtil.dateToStringEMS());

		System.out.println(DateUtil.parseDateWx("20091225091010"));

	}
	public static Date parseDate(String date){
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

	public static Date parseDateWx(String date){
		try {
			return new SimpleDateFormat("yyyyMMddHHmmss").parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
	public static String getYearMonth(){
		return new SimpleDateFormat("yyyy-MM").format(new Date());
	}
	
	public static String getNowDay(){
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}
	
	public static String getNowDayChs(){
		return new SimpleDateFormat("yyyy年MM月dd HH:mm:ss").format(new Date());
	}
	
	public static String getNowTime(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
	}
	
	
	public static String getSeven(){
		Calendar cal=Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_MONTH, -7);

		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}
	
	public static String getSeven(int day){
		Calendar cal=Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_MONTH, day);

		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}


	public static String getNowStr(){
		return new SimpleDateFormat("yyyyMMdd").format(new Date());
	}
	
	/**
	 * @Description:日期转字符串
	 * @author: 神风逐胜
	 * @mail:xixingyingzhongdui@gmail.com
	 * @Date 2014-3-7
	 * @version 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String dateToString(Date date,String pattern){
		if(date!=null){
			SimpleDateFormat sdf=new SimpleDateFormat(pattern);
			return sdf.format(date);
		}
		return "";
	}
	
	public static String dateToString(Date date){
		return dateToString(date,"yyyy-MM-dd hh.mm.ss");
	}
	
	
	/**
	 * @Description:日期转字符串
	 * @author: 神风逐胜
	 * @mail:xixingyingzhongdui@gmail.com
	 * @Date 2014-3-7
	 * @version 
	 * @param
	 * @param
	 * @return
	 */
	public static String dateToStringEMS(){
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd#HH:mm:ss");
		return sdf.format(date).replace("#", "T");
		
	}

	public static String format(Long timestamp,String pattern){
        return  null;
	}
	
	
}
