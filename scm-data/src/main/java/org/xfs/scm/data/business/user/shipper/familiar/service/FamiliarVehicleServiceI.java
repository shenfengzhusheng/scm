package org.xfs.scm.data.business.user.shipper.familiar.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.data.business.user.shipper.familiar.vo.FamiliarVehicleVo;

import java.util.List;

public interface FamiliarVehicleServiceI {

    /**
     * 查找熟车
     * @param vo
     * @return
     */
    @Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
    List<FamiliarVehicleVo> getFamiliarVehicles(FamiliarVehicleVo vo);

    FamiliarVehicleVo getFamiliarVehicle(FamiliarVehicleVo vo);

    /**
     * 添加用户熟车
     * @param vo
     * @return
     */
    @Transactional(readOnly = false,propagation = Propagation.REQUIRED)
    boolean addFamiliarVehicle(FamiliarVehicleVo vo);

    /**
     * 删除
     * @param vo
     * @return
     */
    @Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
    int removeFamiliarVehicle(FamiliarVehicleVo vo);

    /**
     * 修改熟车信息
     * @param vo
     * @return
     */
    @Transactional(readOnly = false,propagation = Propagation.REQUIRED)
    boolean modifyFamiliarVehicle(FamiliarVehicleVo vo);


    Grid<FamiliarVehicleVo> grid(FamiliarVehicleVo vo, int page, int rows);



}
