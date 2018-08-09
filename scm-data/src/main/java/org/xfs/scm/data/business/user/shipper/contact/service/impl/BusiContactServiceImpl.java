package org.xfs.scm.data.business.user.shipper.contact.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.data.business.user.shipper.contact.mapper.BusiContactMapper;
import org.xfs.scm.data.business.user.shipper.contact.service.BusiContactServiceI;
import org.xfs.scm.data.business.user.shipper.contact.vo.BusiContactVo;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 神风逐胜 on 2018/1/27 0027.21:35
 * version:1.0
 */
@Service
public class BusiContactServiceImpl implements BusiContactServiceI {

    @Resource
    private BusiContactMapper busiContactMapper;

    @Transactional(readOnly = false,propagation = Propagation.REQUIRED)
    @Override
    public int removeBusiContact(BusiContactVo vo) {
        return this.busiContactMapper.removeBusiContact(vo);
    }
    @Transactional(readOnly = false,propagation = Propagation.REQUIRED)
    @Override
    public boolean save(BusiContactVo vo) {
        if(this.busiContactMapper.addBusiContact(vo)==1){
            return true;
        }
        return false;
    }

    @Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
    @Override
    public List<BusiContactVo> getBusiContacts(BusiContactVo vo) {
        return this.busiContactMapper.getBusiContacts(vo);
    }
    @Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
    @Override
    public BusiContactVo getBusiContact(BusiContactVo vo) {
       if(vo!=null){
           List<BusiContactVo>list=this.getBusiContacts(vo);
           if(!list.isEmpty()){
               return list.get(0);
           }
       }
        return null;
    }
    @Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
    @Override
    public Grid<BusiContactVo> grid(BusiContactVo vo, int page, int rows) {
        Page<BusiContactVo> pages= PageHelper.startPage(page,rows);
        List<BusiContactVo>list=this.getBusiContacts(vo);
        if(!list.isEmpty()){
            Grid<BusiContactVo> grid=new Grid<BusiContactVo>();
            grid.setTotal(pages.getTotal());
            grid.setRows(list);
            return grid;
        }
        return null;
    }

    @Transactional(readOnly = false,propagation = Propagation.REQUIRED)
    @Override
    public int modifyBusiContact(BusiContactVo vo) {
        return this.busiContactMapper.modifyBusiContact(vo);
    }
}
