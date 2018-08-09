package org.xfs.scm.data.business.enums;


import org.xfs.scm.common.enums.EnumI;

public enum OrderStatusEnum  implements EnumI<Integer> {
    CANCELED(-1, "已取消"),
    WAITING(0, "待接单"),
    ORDERED(1, "已接单"),
    COMPLETE(4, "已签单");
    private Integer code;
    private String name;
    private OrderStatusEnum(Integer code, String name){
        this.code=code;
        this.name=name;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
