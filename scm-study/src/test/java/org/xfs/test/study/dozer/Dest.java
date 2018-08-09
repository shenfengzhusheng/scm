package org.xfs.test.study.dozer;

import java.util.List;

public class Dest {
    private String name;
    private int age;
    private String nick;
    private List<DItem> items;
    private List<DestSku> skus;

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

    @Override
    public String toString() {
        return "Dest [name=" + name + ", age=" + age + ",nick=" + nick + "]";
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public List<DItem> getItems() {
        return items;
    }

    public void setItems(List<DItem> items) {
        this.items = items;
    }

    public List<DestSku> getSkus() {
        return skus;
    }

    public void setSkus(List<DestSku> skus) {
        this.skus = skus;
    }
}
