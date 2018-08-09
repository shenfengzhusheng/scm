package org.xfs.core.business.index.model;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class Person implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1686593935051552115L;

    @NotEmpty(message = "姓名不能为能空！")
    private String name;

    @Max(value = 150, message = "年龄不能大于150岁")
    @Min(value = 0, message = "年龄不能小于0")
    private int age;
    @Email(message = "邮箱格式不合法！")
    private String email;

    private int sex = 0;
    private String addr;
    private String state = "YES";

    /**
     * 手机号 666
     */

    @NotNull(message = "电话不能为空")
    private String mobile;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + ", email=" + email + ", sex=" + sex + ", addr=" + addr + ", state=" + state + "]";
    }
}
