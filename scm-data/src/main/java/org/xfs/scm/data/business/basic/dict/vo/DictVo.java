package org.xfs.scm.data.business.basic.dict.vo;

import org.xfs.scm.data.business.basic.dict.po.DictPo;

public class DictVo extends DictPo {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5208720324119234252L;
	public DictVo(){}
    public DictVo(Long dictId){
        super.setDictId(dictId);
    }
}
