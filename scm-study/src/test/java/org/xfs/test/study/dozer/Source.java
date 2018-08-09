package org.xfs.test.study.dozer;

import java.util.List;

import org.dozer.Mapping;
import org.xfs.core.platform.anntation.mapping.ListMapping;

public class Source {
    private String name;

    private int age;

    @Mapping("nick")
    private String nickName;

    @ListMapping(key = "items", destinationClass = DItem.class)
    private List<SItem> items;

    @ListMapping
    private List<SourceSku> skus;

    public Source() {

    }

    public Source(String name, int age, String nickName) {
        this.name = name;
        this.age = age;
        this.nickName = nickName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public List<SItem> getItems() {
        return items;
    }

    public void setItems(List<SItem> items) {
        this.items = items;
    }

    public List<SourceSku> getSkus() {
        return skus;
    }

    public void setSkus(List<SourceSku> skus) {
        this.skus = skus;
    }
}
