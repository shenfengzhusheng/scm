package org.xfs.scm.data.business.user.shipper.shipper.service.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.xfs.scm.data.business.enums.UserRoleEnum;
import org.xfs.scm.data.business.user.account.mapper.UserBasicMapper;
import org.xfs.scm.data.business.user.account.mapper.UserInfoMapper;
import org.xfs.scm.data.business.user.shipper.contact.mapper.BusiContactMapper;
import org.xfs.scm.data.business.user.shipper.familiar.mapper.FamiliarVehicleMapper;
import org.xfs.scm.data.business.user.shipper.shipper.bo.ShipperBo;
import org.xfs.scm.data.business.user.shipper.shipper.mapper.ShipperMapper;
import org.xfs.scm.data.business.user.shipper.shipper.service.ShipperServiceI;
import org.xfs.scm.data.business.user.shipper.shipper.vo.ShipperVo;
import org.xfs.scm.platform.config.exception.BusinessException;
import org.xfs.scm.platform.util.components.Config;
import org.xfs.scm.platform.util.components.UrlUtil;
import org.xfs.scm.platform.util.string.StringUtil;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 神风逐胜 on 2018/1/18 0018.21:28
 * version:1.0
 */
@Service
public class ShipperServiceImpl implements ShipperServiceI {

    @Resource
    private ShipperMapper shipperMapper;

    @Resource
    private FamiliarVehicleMapper familiarVehicleMapper;

    @Resource
    private BusiContactMapper busiContactMapper;

    @Resource
    private Config config;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private UserBasicMapper userBasicMapper;

    @Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public ShipperBo addShipperBo(ShipperBo bo) {
        if(bo!=null){
            bo.getUserBasic().setUserRole(UserRoleEnum.SHIPPER.getCode());//货主
            if(this.userBasicMapper.addUserBasic(bo.getUserBasic())==1){
                bo.setUserId(bo.getUserBasic().getId());
                bo.getUserInfo().setUserId(bo.getUserId());
                if(this.userInfoMapper.addUserInfo(bo.getUserInfo())!=1){
                    throw new BusinessException("货主注册时生成用户信息失败！","220002");
                }
                ShipperVo vo=new ShipperVo();
                try {
                    BeanUtils.copyProperties(vo,bo);
                } catch (Exception e) {
                    throw new BusinessException("货主对象拷贝异常！","220012");
                }
                if(this.shipperMapper.addShipper(vo)!=1){
                    throw new BusinessException("货主注册时生成用户货主信息失败！","220003");
                }

                return bo;

            }else{
                throw new BusinessException("货主注册时生成用户帐户信息失败！","220001");

            }
        }
        return bo;
    }

    @Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
    @Override
    public List<ShipperBo> getShippers(ShipperVo vo) {
        return buildImgRealPath(this.shipperMapper.getShippers(vo));
    }

    private List<ShipperBo> buildImgRealPath(List<ShipperBo> list){
        if(!list.isEmpty()){
            for(ShipperBo bo:list){
                if(StringUtil.isNotBlank(bo.getUserInfo().getUserHeaderUrl())){
                    bo.getUserInfo().setUserHeaderUrl(config.getImagePath()+ UrlUtil.urlSplit(bo.getUserInfo().getUserHeaderUrl()));
                }
            }
        }
        return list;

    }

    @Override
    public ShipperBo getShipper(ShipperVo vo) {
        if(vo!=null){
            List<ShipperBo> list=this.getShippers(vo);
            if(!list.isEmpty()){
                return list.get(0);
            }
        }
        return null;
    }
}
