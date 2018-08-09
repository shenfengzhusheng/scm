package org.xfs.scm.data.business.user.shipper.familiar.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.data.business.user.shipper.familiar.mapper.FamiliarVehicleMapper;
import org.xfs.scm.data.business.user.shipper.familiar.service.FamiliarVehicleServiceI;
import org.xfs.scm.data.business.user.shipper.familiar.vo.FamiliarVehicleVo;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class FamiliarVehicleServiceImpl implements FamiliarVehicleServiceI {

    @Resource
    private FamiliarVehicleMapper familiarVehicleMapper;

    @Override
    public List<FamiliarVehicleVo> getFamiliarVehicles(FamiliarVehicleVo vo) {
        if(vo.getOwnerUserId().longValue()==-99){
            List<FamiliarVehicleVo>list= new ArrayList<FamiliarVehicleVo>();
            for(int i=0;i<10;i++){
                FamiliarVehicleVo familiarVehicleVo=new FamiliarVehicleVo();
                int v=(i+1);
                familiarVehicleVo.setUserHeadUrl("group:group1;path:M00/00/01/eSrNzFhCdH2AY6rqAAAbldEty-4337.png");
                familiarVehicleVo.setUserIdcardName("测试司机"+v);
                familiarVehicleVo.setUserNickName("花名："+v);
                familiarVehicleVo.setVehicleNum("A0001---"+v);
                familiarVehicleVo.setId(new Long(v));
                familiarVehicleVo.setUserPhone("1501555000"+i);
                familiarVehicleVo.setVehicleCapacity(20);
                familiarVehicleVo.setVehicleLength(450);
                familiarVehicleVo.setVehicleVolume(20);
                familiarVehicleVo.setVehicleType(new Random().nextInt(10));
                familiarVehicleVo.setVehicleTypeName("小型货车");
                list.add(familiarVehicleVo);

            }
            return list;
        }
        return this.familiarVehicleMapper.getFamiliarVehicles(vo);
    }

    @Override
    public FamiliarVehicleVo getFamiliarVehicle(FamiliarVehicleVo vo) {
        if(vo!=null){
            List<FamiliarVehicleVo>list=this.getFamiliarVehicles(vo);
            if(!list.isEmpty()){
                return list.get(0);
            }
        }
        return null;
    }

    @Override
    public boolean addFamiliarVehicle(FamiliarVehicleVo vo) {
        if(this.familiarVehicleMapper.addFamiliarVehicle(vo)==1){
            return true;
        }
        return false;
    }

    @Override
    public int removeFamiliarVehicle(FamiliarVehicleVo vo) {

        return this.familiarVehicleMapper.removeFamiliarVehicle(vo);
    }

    @Override
    public boolean modifyFamiliarVehicle(FamiliarVehicleVo vo) {
        if(this.familiarVehicleMapper.modifyFamiliarVehicle(vo)==1){
            return true;
        }
        return false;
    }

    @Override
    public Grid<FamiliarVehicleVo> grid(FamiliarVehicleVo vo,int page,int rows) {
        Page<FamiliarVehicleVo> pages= PageHelper.startPage(page,rows);
        List<FamiliarVehicleVo>list=this.getFamiliarVehicles(vo);
        if(list.isEmpty()){
            Grid<FamiliarVehicleVo> grid=new Grid<FamiliarVehicleVo>();
            grid.setTotal(pages.getTotal());
            grid.setRows(list);
            return grid;
        }
        return null;
    }
}
