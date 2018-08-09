package org.xfs.scm.data.business.crm.user_customer.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.common.exception.SqlException;
import org.xfs.scm.data.business.crm.user_customer.entity.UserCustomer;
import org.xfs.scm.data.business.crm.user_customer.mapper.UserCustomerMapper;
import org.xfs.scm.data.business.crm.user_customer.service.UserCustomerServiceI;
import org.xfs.scm.data.business.crm.user_customer.vo.GrantUserCustomerVo;
import org.xfs.scm.data.business.crm.user_customer.vo.UserCustomerVo;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserCustomerServiceImpl implements UserCustomerServiceI {
    @Resource
    private UserCustomerMapper userCustomerMapper;
    @Override
    public int removeUserCustomer(UserCustomerVo vo) {
        return this.userCustomerMapper.removeUserCustomer(vo);
    }

    @Override
    public int addUserCustomer(UserCustomerVo vo) {
        return this.userCustomerMapper.addUserCustomer(vo);
    }

    @Override
    public int addUserCustomers(List<UserCustomerVo> list) {
        return this.userCustomerMapper.addUserCustomers(list);
    }

    @Override
    public List<UserCustomer> find(UserCustomerVo vo) {
        return this.userCustomerMapper.getUserCustomers(vo);
    }

    @Override
    public Grid<UserCustomer> grid(UserCustomerVo vo) {
        Page<UserCustomer> page= PageHelper.startPage(vo.getPage(),vo.getRows());
        List<UserCustomer>list=this.find(vo);
        if(!list.isEmpty()){
            Grid<UserCustomer>grid=new Grid<>();
            grid.setTotal(page.getTotal());
            grid.setRows(list);
            return grid;
        }
        return null;
    }

    @Override
    public UserCustomer get(UserCustomerVo vo) {
        List<UserCustomer>list=this.find(vo);
        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    @Override
    public int modifyUserCustomer(UserCustomerVo vo) {
        return this.userCustomerMapper.modifyUserCustomer(vo);
    }

    @Override
    public boolean grantUserCustomer(GrantUserCustomerVo vo) {
        //1、查询出原有用户客户权限
        UserCustomerVo queryOldUserCustomer=new UserCustomerVo();
        queryOldUserCustomer.setUserId(vo.getUserId());
        List<UserCustomer>hadCustomer=this.find(queryOldUserCustomer);

        //2、对比增减情况
        List<UserCustomerVo>addUserCustomers=new ArrayList<>();//增加权限
        List<Long>deleteUserCustomers=new ArrayList<>();//删除权限

        if(hadCustomer==null || hadCustomer.isEmpty()){//用户款授过权
            for(Long custId:vo.getCustIds()){
                addUserCustomers.add(this.buildUserCustomerVo(custId,vo.getUserId(),vo.getDate(),vo.getUserName()));
            }
        }else{
            //对比出增加权限与删除权限
            for (Long custId:vo.getCustIds()){//新增
                boolean isHas=false;
                for(UserCustomer userCustomer:hadCustomer){
                    if(custId.longValue()==userCustomer.getCustId().longValue()){
                        isHas=true;
                        break;
                    }
                }
                if(!isHas){
                    addUserCustomers.add(this.buildUserCustomerVo(custId,vo.getUserId(),vo.getDate(),vo.getUserName()));
                }
            }

            for(UserCustomer userCustomer:hadCustomer){
                boolean isDel=true;
                for (Long custId:vo.getCustIds()) {//删除
                    if(custId.longValue()==userCustomer.getCustId().longValue()){
                        isDel=false;
                        break;
                    }
                }
               if(isDel){
                   deleteUserCustomers.add(userCustomer.getId());
               }

            }
        }
        //3、操作
        if(!deleteUserCustomers.isEmpty()){
            UserCustomerVo delVo=new UserCustomerVo();
            delVo.setIds(deleteUserCustomers);
            if(this.userCustomerMapper.removeUserCustomer(delVo)!=deleteUserCustomers.size()){
                throw new SqlException("删除权限失败！");
            }
        }
        if(!addUserCustomers.isEmpty()){
            if(this.userCustomerMapper.addUserCustomers(addUserCustomers)!=addUserCustomers.size()){
                throw new SqlException("添加用户权限操作异常！");
            }
        }
        return true;
    }
    private UserCustomerVo buildUserCustomerVo(Long custId,Long userId,Date date,String userName){
        UserCustomerVo userCustomerVo=new UserCustomerVo();
        userCustomerVo.setUserId(userId);
        userCustomerVo.setCustId(custId);
        userCustomerVo.setCreatedBy(userName);
        userCustomerVo.setCreatedTime(date);
        userCustomerVo.setLastUpdateBy(userName);
        userCustomerVo.setLastUpdateTime(date);
        return userCustomerVo;
    }
}
