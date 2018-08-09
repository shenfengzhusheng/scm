package org.xfs.scm.goods.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class GoodsModel implements Serializable {
    private String goodsCode;
    private String goodsName;
    private String goodsSpecs;
    private String memo;
    private Map<String,Object> attrs;
    private List<String> photos;

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsSpecs() {
        return goodsSpecs;
    }

    public void setGoodsSpecs(String goodsSpecs) {
        this.goodsSpecs = goodsSpecs;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Map<String, Object> getAttrs() {
        return attrs;
    }

    public void setAttrs(Map<String, Object> attrs) {
        this.attrs = attrs;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    @Override
    public String toString() {
        return "GoodsModel{" +
                "goodsCode='" + goodsCode + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsSpecs='" + goodsSpecs + '\'' +
                ", memo='" + memo + '\'' +
                ", attrs=" + attrs +
                ", photos=" + photos +
                '}';
    }
}
