package org.xfs.test.study.mode.pattern.obersver;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractSubject implements Subject {
	private Set<Observer>list=new HashSet<Observer>();

	@Override
	public void add(Observer observer) {
		list.add(observer);
	}

	@Override
	public void del(Observer observer) {
		list.remove(observer);
	}

	@Override
	public void notifyObservers() {
		if(!list.isEmpty()){
			for(Observer o:list){
				o.update();
			}
		}
	}

	
}
