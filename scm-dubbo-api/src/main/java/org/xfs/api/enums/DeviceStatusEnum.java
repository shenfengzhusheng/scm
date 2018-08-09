package org.xfs.api.enums;

public enum DeviceStatusEnum {
    NORMAL_STATUS("normal","正常"),
    DISABLE_STATUS("disable","禁用"),
    ONLINE_STATUS("online","在线"),
    OUTLINE_STATUS("outline","断网");
            ;

    private String code;
    private String name;
    private DeviceStatusEnum(String code, String name){
        this.code=code;
        this.name=name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getNameByCode(String code){
        for(PayChannelEnum payChannelEnum:PayChannelEnum.values()){
            if(payChannelEnum.getCode().equals(code)){
                return payChannelEnum.getName();
            }
        }
        return null;

    }
    @Override
    public String toString() {
        return "DeviceStatusEnum{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
