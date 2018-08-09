package org.xfs.scm.data.business.user.shipper.shipper.bo;


import org.xfs.scm.data.business.user.account.vo.UserBasicVo;
import org.xfs.scm.data.business.user.account.vo.UserInfoVo;
import org.xfs.scm.data.business.user.shipper.contact.vo.BusiContactVo;
import org.xfs.scm.data.business.user.shipper.familiar.vo.FamiliarVehicleVo;
import org.xfs.scm.data.business.user.shipper.shipper.po.Shipper;

import java.util.List;

/**
 * Created by 神风逐胜 on 2018/1/18 0018.20:50
 * version:1.0
 */
public class ShipperBo extends Shipper{
/**
	 * 
	 */
	private static final long serialVersionUID = -4816594848080834238L;

	//    /**货主别名**/
//    private String userNickName;
//
//    /**货主的头像地址**/
//    private String userHeaderUrl;
//
//    /**公司地址**/
//    private String companyAddress;
//
//    /**公司名字**/
//    private String companyName;
//
//    /**公司的电话**/
//    private String companyPhone;
    private UserBasicVo userBasic;

    private UserInfoVo userInfo;

    private List<BusiContactVo> contacts;

    private List<FamiliarVehicleVo> familiarVehicles;

    public UserBasicVo getUserBasic() {
        return userBasic;
    }

    public void setUserBasic(UserBasicVo userBasic) {
        this.userBasic = userBasic;
    }

    public UserInfoVo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoVo userInfo) {
        this.userInfo = userInfo;
    }

    public List<BusiContactVo> getContacts() {
        return contacts;
    }

    public void setContacts(List<BusiContactVo> contacts) {
        this.contacts = contacts;
    }

    public List<FamiliarVehicleVo> getFamiliarVehicles() {
        return familiarVehicles;
    }

    public void setFamiliarVehicles(List<FamiliarVehicleVo> familiarVehicles) {
        this.familiarVehicles = familiarVehicles;
    }
}
