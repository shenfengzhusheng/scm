package org.xfs.scm.business.pay.account.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.xfs.scm.business.pay.account.entity.PayAccount;
import org.xfs.scm.business.pay.account.mapper.PayAccountMapper;
import org.xfs.scm.business.pay.account.service.PayAccountServiceI;
import org.xfs.scm.business.pay.account.vo.PayAccountVo;
import org.xfs.scm.common.base.model.Grid;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PayAccountServiceImpl implements PayAccountServiceI {

    @Resource
    private PayAccountMapper payAccountMapper;

    @Override
    public int removePayAccount(PayAccountVo vo) {
        return this.payAccountMapper.removePayAccount(vo);
    }

    @Override
    public int addPayAccount(PayAccountVo vo) {
        return this.payAccountMapper.addPayAccount(vo);
    }

    @Override
    public List<PayAccount> getPayAccounts(PayAccountVo vo) {
        return this.payAccountMapper.getPayAccounts(vo );
    }

    @Override
    public PayAccount getPayAccount(PayAccountVo vo) {
        if(vo!=null){
            List<PayAccount> list=this.getPayAccounts(vo);
            if(list!=null && !list.isEmpty()){
                return list.get(0);
            }
        }
        return null;
    }

    @Override
    public Grid<PayAccount> grid(PayAccountVo vo) {
        Grid<PayAccount>grid=null;
        Page<PayAccount> pages= PageHelper.startPage(vo.getPage(),vo.getRows());
        List<PayAccount>list=this.getPayAccounts(vo);
        if(list!=null){
            grid =new Grid<>();
            grid.setRows(list);
            grid.setTotal(pages.getTotal());

        }
        return grid;

    }

    @Override
    public int modifyPayAccount(PayAccountVo vo) {
        return this.payAccountMapper.modifyPayAccount(vo);
    }
}
