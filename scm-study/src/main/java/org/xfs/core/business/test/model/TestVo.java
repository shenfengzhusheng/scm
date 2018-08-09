package org.xfs.core.business.test.model;

import java.io.Serializable;

public class TestVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 774528086327375484L;
	private String key="uuid";
	private String value;
	private int price;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "TestVo [key=" + key + ", value=" + value + ", price=" + price
				+ "]";
	}
}
