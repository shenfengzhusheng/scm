package org.xfs.test.study.mode.pattern.obersver;

public class ObserverTest {

	public static void main(String[] args) {
		Subject subject=new MySubject();
		subject.add(new Observer1());
		subject.add(new Observer2("1"));
		//subject.operator();
		
		subject.add(new Observer2("1"));
		subject.add(new Observer2("2"));
		subject.add(new Observer2("3"));

		subject.add(new Observer2("4"));

		subject.add(new Observer2("2"));

		subject.operator();
		
		subject.del(new Observer2("3"));

		subject.operator();

	}

}
