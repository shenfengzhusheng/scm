package org.xfs.scm.data.business.pay.ali.enums;

import org.xfs.scm.common.base.enums.EnumI;

public enum AliPayStatus implements EnumI<String> {
    WAIT_BUYER_PAY("WAIT_BUYER_PAY","交易创建，等待买家付款"),
    TRADE_PARY	("TRADE_PARY","部份付款"),
    TRADE_CLOSED	("TRADE_CLOSED","未付款交易超时关闭，或支付完成后全额退款"),
    TRADE_SUCCESS("TRADE_SUCCESS","交易支付成功"),
    TRADE_FINISHED("TRADE_FINISHED","交易结束，不可退款");
    private String code;
    private String name;
    private AliPayStatus(String code,String name){
        this.code=code;
        this.name=name;
    }
    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AliPayStatus{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
