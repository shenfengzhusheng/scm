package org.xfs.scm.data.business.basic.areacode.po;

import java.io.Serializable;

public class Area implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -826812540511782893L;

	private String areaCode;

    private String areaName;

    private String areaNamePy;

    private String areaNameShort;

    private Integer areaLevel;

    private String parentAreaCode;

    public Area() {
    }

    public Area(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public String getAreaNamePy() {
        return areaNamePy;
    }

    public void setAreaNamePy(String areaNamePy) {
        this.areaNamePy = areaNamePy == null ? null : areaNamePy.trim();
    }

    public String getAreaNameShort() {
        return areaNameShort;
    }

    public void setAreaNameShort(String areaNameShort) {
        this.areaNameShort = areaNameShort == null ? null : areaNameShort.trim();
    }

    public Integer getAreaLevel() {
        return areaLevel;
    }

    public void setAreaLevel(Integer areaLevel) {
        this.areaLevel = areaLevel;
    }

    public String getParentAreaCode() {
        return parentAreaCode;
    }

    public void setParentAreaCode(String parentAreaCode) {
        this.parentAreaCode = parentAreaCode == null ? null : parentAreaCode.trim();
    }
}