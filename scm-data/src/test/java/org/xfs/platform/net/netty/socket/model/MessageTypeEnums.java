package org.xfs.platform.net.netty.socket.model;

import org.xfs.scm.common.base.enums.EnumI;

public enum MessageTypeEnums implements EnumI<Integer> {
    DEVICE_CONNECT(1,"设备登陆连接"),
    DEVICE_INIT(2,"设备初始化"),
    DEVICE_REPORT(3,"设备状态上报"),
    DEVICE_ALARM(4,"设备破坏警报"),
    DEVICE_OUTAGE(5,"设备停电警报"),
    device_notsignal (6,"设备信号警报")

            ;
    private int code;
    private String name;
    private MessageTypeEnums(Integer code,String name){
        this.code=code;
        this.name=name;
    }
    @Override
    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
