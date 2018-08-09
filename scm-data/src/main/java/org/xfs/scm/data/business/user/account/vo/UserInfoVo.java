package org.xfs.scm.data.business.user.account.vo;

import org.xfs.scm.data.business.user.account.po.UserInfo;

/**
 * Created by 神风逐胜 on 2018/1/18 0018.20:26
 * version:1.0
 */
public class UserInfoVo extends UserInfo {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5299061324491944298L;
	private String proviceCode;
    private String proviceName;

    private String areaCode;
    private String areaName;

    private String cityCode;
    private String cityName;

    public String getProviceCode() {
        return proviceCode;
    }

    public void setProviceCode(String proviceCode) {
        this.proviceCode = proviceCode;
    }

    public String getProviceName() {
        return proviceName;
    }

    public void setProviceName(String proviceName) {
        this.proviceName = proviceName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
