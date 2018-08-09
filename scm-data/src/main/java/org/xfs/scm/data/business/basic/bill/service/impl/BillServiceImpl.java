package org.xfs.scm.data.business.basic.bill.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.data.business.basic.bill.entity.Bill;
import org.xfs.scm.data.business.basic.bill.mapper.BillMapper;
import org.xfs.scm.data.business.basic.bill.service.BillServiceI;
import org.xfs.scm.data.business.basic.bill.vo.BillVo;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BillServiceImpl implements BillServiceI {

    @Resource
    private BillMapper billMapper;
    @Override
    public int remove(BillVo vo) {
        return this.billMapper.removeBill(vo);
    }

    @Override
    public int add(BillVo record) {
        return this.billMapper.addBill(record);
    }

    @Override
    public List<Bill> find(BillVo vo) {
        return this.billMapper.getBills(vo);
    }

    @Override
    public Grid<Bill> grid(BillVo vo) {
        Page<Bill> pages= PageHelper.startPage(vo.getPage(),vo.getRows());
        List<Bill>list=this.find(vo);
        if(!list.isEmpty()){
            Grid<Bill>grid=new Grid<>();

            grid.setTotal(pages.getTotal());
            grid.setRows(list);
            return grid;

        }
        return null;
    }

    @Override
    public Bill get(BillVo vo) {
        List<Bill>list=this.find(vo);
        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    @Override
    public int modify(BillVo record) {
        return this.billMapper.modifyBill(record);
    }
}
