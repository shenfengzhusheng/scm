package org.xfs.scm.map.model.list;

import org.xfs.scm.map.model.DeviceModel;

import java.io.Serializable;
import java.util.List;

public class DeviceResponse implements Serializable {
    private int status;
    private String message;
    private int total;
    private int size;
    private List<DeviceModel> entities;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<DeviceModel> getEntities() {
        return entities;
    }

    public void setEntities(List<DeviceModel> entities) {
        this.entities = entities;
    }
}
