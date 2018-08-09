package org.xfs.scm.map.model;

import org.xfs.scm.map.model.base.BaseMapModel;
import org.xfs.scm.map.model.list.Device_LocationModel;

import java.io.Serializable;

public class DeviceModel extends BaseMapModel{


    private String entity_name;
    private String entity_desc;
    private String column_key;
    private String city;
    private String district;
    private Device_LocationModel latest_location;


    public String getEntity_name() {
        return entity_name;
    }

    public void setEntity_name(String entity_name) {
        this.entity_name = entity_name;
    }

    public String getEntity_desc() {
        return entity_desc;
    }

    public void setEntity_desc(String entity_desc) {
        this.entity_desc = entity_desc;
    }

    public String getColumn_key() {
        return column_key;
    }

    public void setColumn_key(String column_key) {
        this.column_key = column_key;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Device_LocationModel getLatest_location() {
        return latest_location;
    }

    public void setLatest_location(Device_LocationModel latest_location) {
        this.latest_location = latest_location;
    }

    @Override
    public String toString() {
        return "DeviceModel[" +
                "ak='" + super.getAk() + '\'' +
                ", service_id='" + super.getService_id() + '\'' +
                ", entity_name='" + entity_name + '\'' +
                ", entity_desc='" + entity_desc + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", column_key='" + column_key + '\'' +
                ", sn='" + super.getSn() + '\'' +
                ']';
    }
}
