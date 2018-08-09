package org.xfs.scm.data.business.basic.dict.vo;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by 神风逐胜 on 2018/1/12 0012.21:43
 * version:1.0
 */
public class DictInfoVo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6476779823133869105L;

	private String dictTypeCode;

    private String dictTypeName;

    private Integer typeSeq;

    private Set<DictDetailVo> dictSets;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getTypeSeq() {
        return typeSeq;
    }

    public void setTypeSeq(Integer typeSeq) {
        this.typeSeq = typeSeq;
    }

    public String getDictTypeCode() {
        return dictTypeCode;
    }

    public void setDictTypeCode(String dictTypeCode) {
        this.dictTypeCode = dictTypeCode;
    }

    public String getDictTypeName() {
        return dictTypeName;
    }

    public void setDictTypeName(String dictTypeName) {
        this.dictTypeName = dictTypeName;
    }

    public Set<DictDetailVo> getDictSets() {
        return dictSets;
    }

    public void setDictSets(Set<DictDetailVo> dictSets) {
        this.dictSets = dictSets;
    }
}
