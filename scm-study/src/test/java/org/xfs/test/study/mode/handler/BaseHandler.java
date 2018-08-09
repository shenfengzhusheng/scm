package org.xfs.test.study.mode.handler;

public abstract class BaseHandler implements IHandler {
	@Override
	final public void handle(Object object){
		receiver( object); 
		
		backup(object);
		save(object);
		/*解析*/
		doMsg(object);
		/* 回复 */  
		response(object);
	}
}
