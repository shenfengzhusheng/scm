package org.xfs.test.study.mode.handler;

public class HandlerTest {

	public static void main(String[] args) {
		// 收到报文信息  
	    // 判断是AHandler  
	    IHandler aHandler = new AHandler();  
	    aHandler.handle("String");  
	    System.out.println("----------------");  
	  
	    // 有收到一条报文  
	    // 判断是BHandler  
	    IHandler bHandler = new BHandler();  
	    bHandler.handle("String");  
	}

}
