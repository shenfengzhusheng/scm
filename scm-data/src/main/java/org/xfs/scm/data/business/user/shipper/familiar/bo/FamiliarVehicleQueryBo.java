package org.xfs.scm.data.business.user.shipper.familiar.bo;

import java.util.List;

public class FamiliarVehicleQueryBo {
    private Long id;

    private Long ownerUserId;

    private Long driverUserId;

    private Long vehicleId;

    private List<Long> driverIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(Long ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    public Long getDriverUserId() {
        return driverUserId;
    }

    public void setDriverUserId(Long driverUserId) {
        this.driverUserId = driverUserId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public List<Long> getDriverIds() {
        return driverIds;
    }

    public void setDriverIds(List<Long> driverIds) {
        this.driverIds = driverIds;
    }
}
