package org.xfs.scm.data.business.user.shipper.familiar.mapper;

import org.xfs.scm.data.business.user.shipper.familiar.vo.FamiliarVehicleVo;

import java.util.List;

public interface FamiliarVehicleMapper {
    int removeFamiliarVehicle(FamiliarVehicleVo record);

    int addFamiliarVehicle(FamiliarVehicleVo record);

    List<FamiliarVehicleVo> getFamiliarVehicles(FamiliarVehicleVo record);

    int modifyFamiliarVehicle(FamiliarVehicleVo record);

}