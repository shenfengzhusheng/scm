package org.xfs.test.study.mode.pattern.obersver;

public class MySubject extends AbstractSubject {

	@Override
	public void operator() {
		 System.out.println("update self!");  
	     notifyObservers();  
	}

}
