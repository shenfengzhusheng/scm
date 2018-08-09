package org.xfs.scm.data.business.enums;


import org.xfs.scm.common.enums.EnumI;

public enum OrderSrcEnum implements EnumI<Integer> {
    PC(0, "订单来源于pc端"),
    APP(1, "订单来源于app端");

    private int code;
    private String name;

    OrderSrcEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 获取枚举编码
     *
     * @return
     */
    @Override
    public Integer getCode() {
        return this.code;
    }

    /**
     * 获取枚举说明
     *
     * @return
     */
    @Override
    public String getName() {
        return this.name;
    }
}
