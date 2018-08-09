package org.xfs.scm.data.business.basic.areacode.vo;

import org.xfs.scm.data.business.basic.areacode.po.Area;

public class AreaVo extends Area {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3419371971124484740L;
	private String areaLevelWithOpt;

    public String getAreaLevelWithOpt() {
        return areaLevelWithOpt;
    }

    public void setAreaLevelWithOpt(String areaLevelWithOpt) {
        this.areaLevelWithOpt = areaLevelWithOpt;
    }
}
