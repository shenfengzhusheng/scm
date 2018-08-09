package org.xfs.api.enums;

public enum NetWorkEnum {
    LINE_MODEL("LINE_MODEL","有线网络"),
    WIFI_MODEL("WIFI_MODEL","无线网络"),
    SIM_MODEL("SIM_MODEL","移动网络");

    private String code;
    private String name;
    private NetWorkEnum(String code,String name){
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
        return "NetWorkEnum{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
