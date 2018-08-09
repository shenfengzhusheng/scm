package org.xfs.test.study.mode.handler;

public class AHandler extends BaseHandler {

	@Override
	public void receiver(Object object) {
        System.out.println("A的receiver");  
		
	}

	@Override
	public void backup(Object object) {
        System.out.println("A的backup");  
		
	}

	@Override
	public void save(Object object) {
        System.out.println("A的save");  
		
	}

	@Override
	public void doMsg(Object object) {
        System.out.println("A的doMsg");  
		
	}

	@Override
	public void response(Object object) {
        System.out.println("A的response");  
		
	}

}
