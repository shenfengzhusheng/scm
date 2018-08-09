package core.platform.base.enums;

public enum OrderStatus {
    ORDER_STATUS_FAIL("-2", "失效"), ORDER_STATUS_REMOVED("-1", "删除"), ORDER_STATUS_NEW("0", "创建"), ORDER_STATUS_DISPATCH("1", "下发"), ORDER_STATUS_FINSHED(
            "100", "完结");

    private String code;
    private String name;

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

    private OrderStatus(String code, String name) {
        this.code = code;
        this.name = name;
    }

}
