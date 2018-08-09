package org.xfs.test.study.mode.pattern.obersver;

/**
 * subject接口
 * @author xixingyingzhongdui
 *
 */
public interface Subject {
	/**
	 * 添加观察者
	 * project:fm-core
	 * @param observer
	 * author:xifengshan
	 * date:2017年8月24日下午10:59:39
	 */
	void add(Observer observer);
	
	/**
	 * 删除观察者
	 * project:fm-core
	 * @param observer
	 * author:xifengshan
	 * date:2017年8月24日下午10:59:53
	 */
	void del(Observer observer);
    /*通知所有的观察者*/  
	void notifyObservers();
	/*自身操作*/
	void operator();
}
