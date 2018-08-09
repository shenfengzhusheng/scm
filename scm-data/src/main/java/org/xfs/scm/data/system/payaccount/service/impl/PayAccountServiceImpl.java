package org.xfs.scm.data.system.payaccount.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.data.business.crm.cumtomer.entity.Customer;
import org.xfs.scm.data.business.crm.cumtomer.service.CustomerServiceI;
import org.xfs.scm.data.business.crm.cumtomer.vo.CustomerVo;
import org.xfs.scm.data.business.pay.record.po.PayRecordPo;
import org.xfs.scm.data.system.payaccount.entity.PayAccount;
import org.xfs.scm.data.system.payaccount.mapper.PayAccountMapper;
import org.xfs.scm.data.system.payaccount.po.PayAccountPo;
import org.xfs.scm.data.system.payaccount.service.PayAccountServiceI;
import org.xfs.scm.data.system.payaccount.vo.PayAccountVo;
import org.xfs.scm.platform.config.redis.service.CacheServiceI;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PayAccountServiceImpl implements PayAccountServiceI {

    @Resource
    private PayAccountMapper payAccountMapper;

    @Resource
    private CustomerServiceI customerService;
    @Resource
    private CacheServiceI cacheService;

    @Override
    public int remove(PayAccountVo vo) {
        return this.payAccountMapper.removePayAccount(vo);
    }

    @Override
    public int add(PayAccountVo vo) {
        return this.payAccountMapper.addPayAccount(vo);
    }

    @Override
    public List<PayAccountPo> find(PayAccountVo vo) {
        return this.buildCustomer(this.payAccountMapper.getPayAccounts(vo ));
    }
    private List<PayAccountPo> buildCustomer(List<PayAccountPo> list){
        if(!list.isEmpty()){
            Set<Long> custIds=new HashSet<>();
            for(PayAccountPo payAccountPo:list){
                custIds.add(new Long(payAccountPo.getCustId()));
            }
            CustomerVo vo=new CustomerVo();
            vo.setCustIds(custIds);
            List<Customer>customers=this.customerService.getCustomers(vo);
            if(!customers.isEmpty()){
                for(PayAccountPo payAccountPo:list){
                    for(Customer customer:customers){
                        if(payAccountPo.getCustId().longValue()==customer.getCustId().longValue()){
                            payAccountPo.setCustomer(customer);
                        }
                    }
                }
            }
        }
        return list;
    }
    @Override
    public PayAccountPo get(PayAccountVo vo) {
        if(vo!=null){
            List<PayAccountPo> list=this.find(vo);
            if(list!=null && !list.isEmpty()){
                return list.get(0);
            }
        }
        return null;
    }

    @Override
    public PayAccountPo getById(Integer id) {
        return this.payAccountMapper.getPayAccount(new PayAccountVo(id));
    }
    @Override
    public PayAccountPo getByAppId(String appId) {
        PayAccountPo po=this.payAccountMapper.getPayAccount(new PayAccountVo(appId));
        if(po!=null){
            CustomerVo customerVo=new CustomerVo(po.getCustId());
            customerVo.setSuperFlag(1);
            Customer customer=this.customerService.getCustomer(customerVo);
            if(customer!=null){
                po.setCustomer(customer);
            }

        }
        return po;
    }

    @Override
    public PayAccountPo getFormCache(String appid) {
        PayAccountPo payAccountPo = this.cacheService.getObject("PAYACCOUNT:" + appid, PayAccountPo.class);
        if(payAccountPo==null){
            payAccountPo=this.getByAppId(appid);
            this.cacheService.setObject("PAYACCOUNT:"+appid,payAccountPo,60*60*24*7);
        }
        return payAccountPo;
    }

    @Override
    public Grid<PayAccountPo> grid(PayAccountVo vo) {
        Grid<PayAccountPo>grid=null;
        Page<PayAccountPo> pages= PageHelper.startPage(vo.getPage(),vo.getRows());
        List<PayAccountPo>list=this.find(vo);
        if(list!=null){
            grid =new Grid<>();
            grid.setRows(list);
            grid.setTotal(pages.getTotal());

        }
        return grid;

    }

    @Override
    public int modify(PayAccountVo vo) {
        return this.payAccountMapper.modifyPayAccount(vo);
    }
}
