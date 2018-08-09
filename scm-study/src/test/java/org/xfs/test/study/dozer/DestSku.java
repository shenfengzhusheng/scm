package org.xfs.test.study.dozer;


public class DestSku {
    private int id;
    private String code;
    private String barcode;
    private Double price;
    private Double len;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "DestSku [id=" + id + ", code=" + code + ", barcode=" + barcode + ", price=" + price + ",len=" + len + "]";
    }

    public Double getLen() {
        return len;
    }

    public void setLen(Double len) {
        this.len = len;
    }
}
