package org.xfs.scm.data.business.user.driver.driver.bo;


import org.xfs.scm.data.business.user.account.vo.UserBasicVo;
import org.xfs.scm.data.business.user.account.vo.UserInfoVo;
import org.xfs.scm.data.business.user.driver.empty.vo.EmptyVehicleVo;
import org.xfs.scm.data.business.user.driver.line.po.DriverLiner;
import org.xfs.scm.data.business.user.driver.vehicle.vo.UserVehicleVo;

import java.io.Serializable;
import java.util.List;

public class DriverBo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3404592234733798372L;

	private UserInfoVo userInfo;

    private UserBasicVo userBasic;

    private List<DriverLiner> liners;

    private EmptyVehicleVo emptyVehicle;

    private UserVehicleVo userVehicle;

    public UserInfoVo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoVo userInfo) {
        this.userInfo = userInfo;
    }

    public UserBasicVo getUserBasic() {
        return userBasic;
    }

    public void setUserBasic(UserBasicVo userBasic) {
        this.userBasic = userBasic;
    }

    public List<DriverLiner> getLiners() {
        return liners;
    }

    public void setLiners(List<DriverLiner> liners) {
        this.liners = liners;
    }

    public EmptyVehicleVo getEmptyVehicle() {
        return emptyVehicle;
    }

    public void setEmptyVehicle(EmptyVehicleVo emptyVehicle) {
        this.emptyVehicle = emptyVehicle;
    }

    public UserVehicleVo getUserVehicle() {
        return userVehicle;
    }

    public void setUserVehicle(UserVehicleVo userVehicle) {
        this.userVehicle = userVehicle;
    }
}
