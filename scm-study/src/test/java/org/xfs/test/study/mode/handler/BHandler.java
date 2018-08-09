package org.xfs.test.study.mode.handler;

public class BHandler extends BaseHandler {

	@Override
	public void receiver(Object object) {
        System.out.println("B的receiver");  
		
	}

	@Override
	public void backup(Object object) {
        System.out.println("B的backup");  
		
	}

	@Override
	public void save(Object object) {
        System.out.println("B的save");  
		
	}

	@Override
	public void doMsg(Object object) {
        System.out.println("B的doMsg");  
		
	}

	@Override
	public void response(Object object) {
        System.out.println("B的response");  
		
	}

}
