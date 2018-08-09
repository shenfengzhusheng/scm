package org.xfs.scm.data.business.user.driver.driver.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.xfs.scm.data.business.enums.UserRoleEnum;
import org.xfs.scm.data.business.user.account.mapper.UserBasicMapper;
import org.xfs.scm.data.business.user.account.mapper.UserInfoMapper;
import org.xfs.scm.data.business.user.driver.driver.bo.DriverBo;
import org.xfs.scm.data.business.user.driver.driver.service.DriverServiceI;
import org.xfs.scm.data.business.user.driver.empty.mapper.EmptyVehicleMapper;
import org.xfs.scm.data.business.user.driver.line.mapper.DriverLinerMapper;
import org.xfs.scm.data.business.user.driver.vehicle.mapper.UserVehicleMapper;
import org.xfs.scm.platform.config.exception.BusinessException;

import javax.annotation.Resource;

@Service
public class DriverServiceImpl implements DriverServiceI {
    @Resource
    private UserBasicMapper userBasicMapper;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private UserVehicleMapper userVehicleMapper;

    @Resource
    private EmptyVehicleMapper emptyVehicleMapper;

    @Resource
    private DriverLinerMapper driverLinerMapper;


    @Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public DriverBo addDriverBo(DriverBo bo) {
        bo.getUserBasic().setUserRole(UserRoleEnum.DRIVER.getCode());
        if(this.userBasicMapper.addUserBasic(bo.getUserBasic())==1){
            Long userId=bo.getUserBasic().getId();
            bo.getUserInfo().setUserId(userId);
            if(this.userInfoMapper.addUserInfo(bo.getUserInfo())!=1){
                throw new BusinessException("司机注册生成用户信息失败！","210002");
            }
        }else{
            throw new BusinessException("司机注册时生成用户帐户信息失败！","210001");

        }
        return null;
    }


}
