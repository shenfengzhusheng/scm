package org.xfs.scm.data.business.enums;


import org.xfs.scm.common.enums.EnumI;

public enum UserRoleEnum implements EnumI<Integer> {
    DRIVER(2,"司机"),
    SHIPPER(1,"货主");

    private Integer code;
    private String name;

    private UserRoleEnum(Integer code, String name){
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
