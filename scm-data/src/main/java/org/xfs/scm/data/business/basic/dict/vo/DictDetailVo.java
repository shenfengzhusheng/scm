package org.xfs.scm.data.business.basic.dict.vo;

import java.io.Serializable;

/**
 * Created by 神风逐胜 on 2018/1/12 0012.21:44
 * version:1.0
 */
public class DictDetailVo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1682464287785369386L;

	private Long dictId;

    private String dictTypeCode;

    private String dictCode;

    private String dictName;

    private Integer seq;

    public Long getDictId() {
        return dictId;
    }

    public void setDictId(Long dictId) {
        this.dictId = dictId;
    }

    public String getDictTypeCode() {
        return dictTypeCode;
    }

    public void setDictTypeCode(String dictTypeCode) {
        this.dictTypeCode = dictTypeCode;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }
}
