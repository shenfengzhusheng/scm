package org.xfs.scm.data.business.device.device.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.data.business.crm.cumtomer.entity.Customer;
import org.xfs.scm.data.business.crm.cumtomer.service.CustomerServiceI;
import org.xfs.scm.data.business.crm.cumtomer.vo.CustomerVo;
import org.xfs.scm.data.business.device.device.mapper.DeviceMapper;
import org.xfs.scm.data.business.device.device.po.DevicePo;
import org.xfs.scm.data.business.device.device.service.DeviceServiceI;
import org.xfs.scm.data.business.device.device.vo.DeviceVo;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DeviceServiceImpl implements DeviceServiceI {

    @Resource
    private DeviceMapper deviceMapper;

    @Resource
    private CustomerServiceI customerService;

    @Override
    public int remove(DeviceVo vo) {
        return this.deviceMapper.removeDevice(vo);
    }

    @Override
    public int add(DeviceVo vo) {
        return this.deviceMapper.addDevice(vo);
    }

    @Override
    public List<DevicePo> find(DeviceVo vo) {
        return this.buildCustomer(this.deviceMapper.getDevices(vo));
    }

    private List<DevicePo> buildCustomer(List<DevicePo> list){
        if(!list.isEmpty()){
            Set<Long> custIds=new HashSet<>();
            for(DevicePo device:list){
                custIds.add(device.getCustId());
            }
            CustomerVo vo=new CustomerVo();
            vo.setCustIds(custIds);
            List<Customer>customers=this.customerService.getCustomers(vo);
            if(!customers.isEmpty()){
                for(DevicePo device:list){
                   for(Customer customer:customers){
                       if(device.getCustId().longValue()==customer.getCustId().longValue()){
                           device.setCustomer(customer);
                       }
                   }
                }
            }
        }
        return list;
    }

    @Override
    public Grid<DevicePo> grid(DeviceVo vo) {
        Page<DevicePo>page= PageHelper.startPage(vo.getPage(),vo.getRows());
        List<DevicePo>list=this.find(vo);
        if(!list.isEmpty()){
            Grid<DevicePo>grid=new Grid<DevicePo>();
            grid.setTotal(page.getTotal());
            grid.setRows(list);
            return grid;
        }
        return null;
    }

    @Override
    public DevicePo get(DeviceVo vo) {
        if(vo!=null){
            List<DevicePo>list=this.find(vo);
            if(!list.isEmpty()){
                return list.get(0);
            }
        }
        return null;

    }

    @Override
    public int modify(DeviceVo vo) {
        return this.deviceMapper.modifyDevice(vo);
    }
}
