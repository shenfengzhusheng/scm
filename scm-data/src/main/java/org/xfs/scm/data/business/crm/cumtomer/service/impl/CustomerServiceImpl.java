package org.xfs.scm.data.business.crm.cumtomer.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.data.business.crm.cumtomer.entity.Customer;
import org.xfs.scm.data.business.crm.cumtomer.mapper.CustomerMapper;
import org.xfs.scm.data.business.crm.cumtomer.service.CustomerServiceI;
import org.xfs.scm.data.business.crm.cumtomer.vo.CustomerVo;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CustomerServiceImpl  implements CustomerServiceI{

    @Resource
    private CustomerMapper customerMapper;


    @Override
    public int removeCustomer(CustomerVo vo) {
        return this.customerMapper.removeCustomer(vo);
    }

    @Override
    public int addCustomer(CustomerVo vo) {
        return this.customerMapper.addCustomer(vo);
    }

    @Override
    public List<Customer> getCustomers(CustomerVo vo) {
        return this.customerMapper.getCustomers(vo);
    }

    @Override
    public Customer getCustomer(CustomerVo vo) {
        if(vo!=null){
            List<Customer> list=this.getCustomers(vo);
            if(!list.isEmpty()){
                return list.get(0);
            }
        }
        return null;
    }

    @Override
    public Grid<Customer> grid(CustomerVo vo) {
        Page<Customer> pages= PageHelper.startPage(vo.getPage(),vo.getRows());
        List<Customer>list=this.getCustomers(vo);
        if(!list.isEmpty()){
            Grid<Customer>grid=new Grid<>();
            grid.setTotal(pages.getTotal());
            grid.setRows(pages.getResult());
            return grid;
        }
        return null;
    }


    @Override
    public int modifyCustomer(Customer record) {
        return this.customerMapper.modifyCustomer(record);
    }
}
