package org.xfs.scm.data.business.busi.order.service;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import org.xfs.scm.BaseTest;
import org.xfs.scm.common.utils.IdGenerator;
import org.xfs.scm.data.business.busi.contact.vo.BusiOrderContactVo;
import org.xfs.scm.data.business.busi.order.bo.BusiOrderBo;
import org.xfs.scm.data.business.busi.order.vo.BusiOrderGoodsVo;
import org.xfs.scm.data.business.busi.order.vo.BusiOrderVo;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BusiServiceImpTest  extends BaseTest{

    @Resource
    private BusiOrderServiceI service;

    @Test
    public void testAdd(){
       for(int i=0;i<30;i++){
            BusiOrderBo vo=new BusiOrderBo();
            vo.setOrderId(new IdGenerator().nextId1());
            vo.setBidType(1);
            vo.setCreateTime(new Date());
            //vo.setCancelType(1);
            vo.setDriverUserId(2L);
            vo.setOrderSrc(1);
            vo.setGoodsLoadTime(new Date());
            vo.setOwnerRemark("ok");
            vo.setShareType(0);
            vo.setNeedInvoice(1);
            vo.setNeedReceipt(1);
            vo.setUpdateTime(new Date());
            vo.setPayStatus(0);
            vo.setExpectPrice(555);
            vo.setOrderPrice(11);
            vo.setOrderStatus(0);
            vo.setOwnerUserId(1L);
            vo.setOrderBatch("1");
            System.out.println("id is:"+vo.getId());
            BusiOrderContactVo contactVo=new BusiOrderContactVo();
            contactVo.setOrderId(vo.getOrderId());
            contactVo.setEndAddrCode("120000:天津市;120100:市辖区;120101:和平区");
            contactVo.setEndAddrDetail("滨海新区");
            contactVo.setStartAddrCode("110000:北京市;110100:市辖区;110101:东城区");
            contactVo.setStartAddrDetail("海淀区");
            contactVo.setStartContactName("林晓茹");
            contactVo.setStartContactPhone("18860052855");
            contactVo.setEndContactPhone("18860052855");
            contactVo.setEndContactName("林晓茹");
            vo.setContact(contactVo);
            List<BusiOrderGoodsVo> goods=new ArrayList<BusiOrderGoodsVo>();
            BusiOrderGoodsVo goodsVo =new BusiOrderGoodsVo();
            goodsVo.setOrderId(vo.getOrderId());
            goodsVo.setGoodsName("食品/20m³/0.5吨");
            goodsVo.setGoodsType(4);
            goodsVo.setGoodsVolume(5);
            goodsVo.setGoodsWeight(5000);
            goodsVo.setVehicleLength(420);
            goodsVo.setVehicleNum(1);
            goodsVo.setVehicleType(11);
            goods.add(goodsVo);
            vo.setGoods(goods);

            BusiOrderVo b=new BusiOrderVo();
        try {
            BeanUtils.copyProperties(b,vo);
            System.out.println(b.getOrderId());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        this.service.addBusiOrderVo(b);
          //  System.out.println("-------------------------------id is:"+vo.getId() );
           try {
               Thread.sleep(50);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }


    }


    @Test
    public void testGrid(){
//        Grid<BusiOrderVo> grid=this.service.grid(null,2,5);
//        if(grid!=null){
//            for(BusiOrderVo vo:grid.getRows()){
//                System.out.println(vo.getOrderId());
//            }
//        }
    }

}
