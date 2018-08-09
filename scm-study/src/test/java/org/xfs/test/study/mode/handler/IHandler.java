package org.xfs.test.study.mode.handler;

public interface IHandler {
	
	void handle(Object object);
	/* 接收内容*/
	void receiver(Object object); 
	
	void backup(Object object);
	void save(Object object);
	/*解析*/
	void doMsg(Object object);
	/* 回复 */  
	void response(Object object);
}
