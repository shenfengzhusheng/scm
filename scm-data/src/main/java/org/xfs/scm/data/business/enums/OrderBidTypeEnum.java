package org.xfs.scm.data.business.enums;


import org.xfs.scm.common.enums.EnumI;

public enum OrderBidTypeEnum implements EnumI<Integer> {
    PLATEFORM_BID(0, "全网竞价"),
    APPOINT_BID(1, "指定单个熟车竞价"),
    FAMILY_BID(2, "指定多个熟车竞价");
    private OrderBidTypeEnum(Integer code, String name){
        this.code=code;
        this.name=name;
    }
    private Integer code;
    private String name;
    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
