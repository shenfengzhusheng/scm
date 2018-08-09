package org.xfs.core.platform.enums;

/**
 * 
 * @author xixingyingzhongdui
 *
 */
public enum PropertyModel {
	DEVELOPER("dev","/config/properties/dev"),
	LOCAL("uat","/config/properties/local"),
	UAT("uat","/config/properties/uat"),
	QA("uat","/config/properties/qa"),
	PRODUCTION("product","/config/properties/prod");
	private String model;
	private String path;
	private PropertyModel(String model,String path){
		this.model=model;
		this.path=path;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	@Override
	public String toString(){
		return this.model+"="+this.path;
	}
	
	public static String getPath(String model){
		for(PropertyModel pm:PropertyModel.values()){
			if(pm.model.equals(model)){
				return pm.getPath();
			}
		}
		return null;
	}
}
