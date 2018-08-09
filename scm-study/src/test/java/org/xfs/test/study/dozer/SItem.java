package org.xfs.test.study.dozer;

import org.dozer.Mapping;

public class SItem {
    @Mapping("goodId")
    private int itemId;
    @Mapping("goodName")
    private String itemName;
    @Mapping("goodCode")
    private String itemCode;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public SItem() {}

    public SItem(int itemId, String itemName, String itemCode) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemCode = itemCode;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }
}
