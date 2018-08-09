package org.xfs.test.study.mode.pattern.obersver;

public class Observer2 implements Observer {
	private String code;
	public Observer2(String code){
		this.code=code;
	}
	@Override
	public void update() {
        System.out.println("observer2 "+code+" has received!");  
		
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Observer2 other = (Observer2) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
	

}
