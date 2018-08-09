package org.xfs.scm.data.business.busi.order.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.common.utils.IdGenerator;
import org.xfs.scm.data.business.busi.contact.mapper.BusiOrderContactMapper;
import org.xfs.scm.data.business.busi.log.mapper.BusiOrderLogMapper;
import org.xfs.scm.data.business.busi.log.vo.BusiOrderLogVo;
import org.xfs.scm.data.business.busi.order.mapper.BusiOrderGoodsMapper;
import org.xfs.scm.data.business.busi.order.mapper.BusiOrderMapper;
import org.xfs.scm.data.business.busi.order.po.BusiOrder;
import org.xfs.scm.data.business.busi.order.service.BusiOrderServiceI;
import org.xfs.scm.data.business.busi.order.vo.BusiOrderGoodsVo;
import org.xfs.scm.data.business.busi.order.vo.BusiOrderVo;
import org.xfs.scm.data.business.enums.OrderStatusEnum;
import org.xfs.scm.data.business.user.shipper.shipper.bo.ShipperBo;
import org.xfs.scm.data.business.user.shipper.shipper.service.ShipperServiceI;
import org.xfs.scm.data.business.user.shipper.shipper.vo.ShipperVo;
import org.xfs.scm.platform.config.exception.BusinessException;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BusiOrderServiceImpl implements BusiOrderServiceI {
    @Resource
    private BusiOrderMapper orderMapper;

    @Resource
    private BusiOrderGoodsMapper orderGoodsMapper;

    @Resource
    private BusiOrderContactMapper orderContactMapper;

    @Resource
    private BusiOrderLogMapper orderLogMapper;

    @Resource
    private ShipperServiceI shipperServiceI;


    @Override
    public int removeBusiOrder(BusiOrderVo vo) {
        return 0;
    }

    @Override
    public int addBusiOrder(BusiOrderVo vo) {
        if(vo.getCreateTime()==null){
            vo.setCreateTime(new Date());
        }
        if(vo.getUpdateTime()==null){
            vo.setUpdateTime(new Date());
        }
        vo.setOrderStatus(0);
        return this.orderMapper.addBusiOrder(vo);
    }

    @Override
    public List<BusiOrder> getBusiOrders(BusiOrderVo vo) {
        return this.orderMapper.getBusiOrders(vo);
    }

    @Override
    public BusiOrder getBusiOrder(BusiOrderVo vo) {
        List<BusiOrder>list=this.getBusiOrders(vo);
        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    @Override
    public int modifyBusiOrder(BusiOrderVo vo) {
        return this.modifyBusiOrder(vo);
    }

    @Override
    public List<BusiOrderVo> gridBusiOrders(BusiOrderVo vo) {
        return this.orderMapper.gridBusiOrders(vo);
    }

    @Override
    public BusiOrderVo getBusiOrderVo(BusiOrderVo vo) {
        List<BusiOrderVo> list=this.gridBusiOrders(vo);
        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    @Transactional(readOnly = false,propagation= Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public BusiOrderVo addBusiOrderVo(BusiOrderVo vo) {
        vo.setOrderStatus(OrderStatusEnum.WAITING.getCode());
        vo.setOrderId(new IdGenerator().nextId1());
        //1、添加订单主表
        if(this.orderMapper.addBusiOrder(vo)==1){
            //2、添加订单明细表
            for(BusiOrderGoodsVo goodsVo:vo.getGoods()){
                goodsVo.setGoodsName(goodsVo.getGoodsTypeName()+"/"+goodsVo.getGoodsVolume()+"m³"+"/"+goodsVo.getGoodsWeight()+"吨");
                goodsVo.setOrderId(vo.getOrderId());
                if(this.orderGoodsMapper.addBusiOrderGoods(goodsVo)!=1){
                    throw  new BusinessException("生成订单时插入货物信息失败！","110002");
                }
            }
            StringBuilder startAreaCode=new StringBuilder("");
            startAreaCode.append(vo.getContact().getStartProviceCode()).append(":").append(vo.getContact().getStartProviceName()).append(";")
                .append(vo.getContact().getStartAreaCode()).append(":").append(vo.getContact().getStartAreaName()).append(";")
                .append(vo.getContact().getStartCityCode()).append(":").append(vo.getContact().getStartCityName());
            vo.getContact().setStartAddrCode(startAreaCode.toString());
            StringBuilder endAddrCode=new StringBuilder("");
            endAddrCode.append(vo.getContact().getEndProviceCode()).append(":").append(vo.getContact().getEndProviceName()).append(";")
                    .append(vo.getContact().getEndAreaCode()).append(":").append(vo.getContact().getEndAreaName()).append(";")
                    .append(vo.getContact().getEndCityCode()).append(":").append(vo.getContact().getEndCityName());
            vo.getContact().setEndAddrCode(endAddrCode.toString());
            //3、生成订单收发货人信息
            vo.getContact().setOrderId(vo.getOrderId());
            if(this.orderContactMapper.insertSelective(vo.getContact())!=1){
                throw  new BusinessException("生成订单联系人异常！","110003");
            }

            //4、生成订单日志
            BusiOrderLogVo logVo=new BusiOrderLogVo();
            logVo.setCreateTime(vo.getCreateTime());
            logVo.setRemark("下单");
            logVo.setOrderStatusTo(-999);
            logVo.setOrderStatusTo(0);
            logVo.setOrderId(vo.getOrderId());
            logVo.setOpType(1);
            logVo.setOpMid(1L);
            if(this.orderLogMapper.insertSelective(logVo)!=1){
                throw  new BusinessException("生成订单日志异常！","110004");
            }
        }else{
            throw  new BusinessException("生成订单信息异常！","110001");
        }
        return vo;
    }

    @Override
    public Grid<BusiOrderVo> grid(BusiOrderVo vo, Integer page, Integer rows) {
        Page<BusiOrderVo> pages=PageHelper.startPage(page,rows);
        List<BusiOrderVo>list=this.orderMapper.gridBusiOrders(vo);
        if(!list.isEmpty()){
            this.queryShipper(list);
            Grid<BusiOrderVo> grid=new Grid<BusiOrderVo>();
            grid.setTotal(pages.getTotal());
            grid.setRows(list);
            return grid;
        }

        return null;
    }

    private void queryShipper(List<BusiOrderVo>list){
        if(!list.isEmpty()){
            List<Long>shiperIds=new ArrayList<Long>();
            for(BusiOrderVo bov:list){
                shiperIds.add(bov.getOwnerUserId());
            }
            if(!shiperIds.isEmpty()){
                ShipperVo shipperVo=new ShipperVo();
                shipperVo.setShipperIds(shiperIds);
                List<ShipperBo>shipperBos=this.shipperServiceI.getShippers(shipperVo);
                if(!shipperBos.isEmpty()){
                    for (BusiOrderVo bov:list){
                        for (ShipperBo shipperBo:shipperBos){
                            if(bov.getOwnerUserId().longValue()==shipperBo.getUserId().longValue()){
                                bov.setShipperBo(shipperBo);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}
