package org.xfs.scm.data.business.user.shipper.shipper.service.impl;

import org.junit.Test;
import org.xfs.scm.BaseTest;
import org.xfs.scm.data.business.user.account.po.UserInfo;
import org.xfs.scm.data.business.user.account.vo.UserBasicVo;
import org.xfs.scm.data.business.user.account.vo.UserInfoVo;
import org.xfs.scm.data.business.user.shipper.shipper.bo.ShipperBo;
import org.xfs.scm.data.business.user.shipper.shipper.service.ShipperServiceI;
import org.xfs.scm.data.business.user.shipper.shipper.vo.ShipperVo;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 神风逐胜 on 2018/1/18 0018.21:55
 * version:1.0
 */
public class ShipperServiceImplTest extends BaseTest {

    @Resource
    private ShipperServiceI shipperService;


    @Test
    public void testAdd(){
        ShipperBo bo=new ShipperBo();
        bo.setCompanyBusiLicenseUrl("group:group1;path:M00/00/01/eSrNzFhaJ6SAIGEqAATeoGG1cmw058.png");
        bo.setCompanyAddress("顺德区北窖镇林头村美的大道1号");
        bo.setCompanyName("美的");
        bo.setCompanyStatus(0);
        bo.setCreateTime(new Date());
        bo.setUpdateTime(new Date());
        UserInfoVo userInfoVo=new UserInfoVo();
        userInfoVo.setUserNickName("老何");
        userInfoVo.setUserSex(1);
        userInfoVo.setUserHeaderUrl("group:group1;path:M00/00/02/eSrNzFhspU-Ace-hAAB4yNudIjQ230.jpg");
        userInfoVo.setUserBirth("1976-12-22");
        userInfoVo.setUserEmail("123@qq.com");
        userInfoVo.setUserAddr("440000:广东省;440600:佛山市;440606:顺德区");
        userInfoVo.setUserAddrDetail("顺德区北窖镇林头村美的大道1号");
        userInfoVo.setUserIdcardName("何享键");
        userInfoVo.setUserIdcardBackUrl("group:group1;path:M00/00/00/eSrNzFofnTqAG2DFAADhjPqF1Rs92.jpeg");
        userInfoVo.setUserStatus(1);
        userInfoVo.setCreateTime(new Date());
        userInfoVo.setUpdateTime(userInfoVo.getCreateTime());
        bo.setUserInfo(userInfoVo);

        UserBasicVo userBasicVo=new UserBasicVo();
        userBasicVo.setUpdateTime(new Date());
        userBasicVo.setCreateTime(new Date());
        userBasicVo.setUserLoginName("test2");
        userBasicVo.setUserPwd("e70a868794750c521497f9a422698f54");
        userBasicVo.setUserPwdSalt("NoHdG4L8pv");
        userBasicVo.setUserInviteCode("5555");
        userBasicVo.setUserStatus(1L);
        userBasicVo.setUserPhone("15012345679");
        bo.setUserBasic(userBasicVo);

        this.shipperService.addShipperBo(bo);

    }

    @Test
    public void testGet(){
        ShipperVo vo=new ShipperVo();
        List<Long> list=new ArrayList<Long>();
        list.add(2L);
        vo.setShipperIds(list);
        List<ShipperBo> shipperVos=this.shipperService.getShippers(vo);
        for(ShipperBo shipperVo:shipperVos){
            System.out.println(shipperVo.getCompanyName());
        }
    }
}
