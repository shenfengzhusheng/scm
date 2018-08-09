package org.xfs.api.enums;

import java.io.Serializable;

public enum  PayChannelEnum implements Serializable {
    ALI_PAY("ali","阿里支付"),
    WX_PAY("wx","微信支付");
    private String code;
    private String name;
    private PayChannelEnum(String code,String name){
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
        return "PayChannelEnum{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
    public static void main(String []args){
        System.out.println(PayChannelEnum.WX_PAY.getCode());
    }
}
