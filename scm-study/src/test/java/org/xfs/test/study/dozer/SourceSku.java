package org.xfs.test.study.dozer;

import java.math.BigDecimal;

import org.dozer.Mapping;

public class SourceSku {
    @Mapping("id")
    private int skuId;
    @Mapping("code")
    private String skuCode;

    @Mapping("barcode")
    private String skucBarcode;
    @Mapping("price")
    private BigDecimal skuPrice;

    @Mapping("len")
    private String length;

    public SourceSku() {}

    public SourceSku(int skuId, String skuCode, String skucBarcode, BigDecimal skuPrice, String length) {
        this.skuId = skuId;
        this.skuCode = skuCode;
        this.skucBarcode = skucBarcode;
        this.skuPrice = skuPrice;
        this.length = length;
    }

    public int getSkuId() {
        return skuId;
    }

    public void setSkuId(int skuId) {
        this.skuId = skuId;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getSkucBarcode() {
        return skucBarcode;
    }

    public void setSkucBarcode(String skucBarcode) {
        this.skucBarcode = skucBarcode;
    }

    public BigDecimal getSkuPrice() {
        return skuPrice;
    }

    public void setSkuPrice(BigDecimal skuPrice) {
        this.skuPrice = skuPrice;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

}
