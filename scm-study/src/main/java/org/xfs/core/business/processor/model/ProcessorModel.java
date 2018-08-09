package org.xfs.core.business.processor.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ProcessorModel implements Serializable {
    private String name;
    private String code;
    private String version;

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ProcessorModel [name=" + name + ", code=" + code + ",version=" + version + "]";
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
