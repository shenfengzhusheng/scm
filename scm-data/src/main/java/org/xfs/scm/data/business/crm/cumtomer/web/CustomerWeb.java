package org.xfs.scm.data.business.crm.cumtomer.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.common.base.model.JsonResponse;
import org.xfs.scm.common.utils.BeanUtils;
import org.xfs.scm.data.business.crm.cumtomer.entity.Customer;
import org.xfs.scm.data.business.crm.cumtomer.po.TreeCustomer;
import org.xfs.scm.data.business.crm.cumtomer.service.CustomerServiceI;
import org.xfs.scm.data.business.crm.cumtomer.vo.CustomerVo;
import org.xfs.scm.platform.base.web.BaseWeb;
import org.xfs.scm.platform.config.shiro.realm.TokenManager;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/rest/crm/customer/")
public class CustomerWeb extends BaseWeb{

    @Resource
    private CustomerServiceI customerService;


    @ResponseBody
    @RequestMapping(value = "save.do",method = RequestMethod.POST)
    public Object save(CustomerVo data){
        data.setCreatedBy(TokenManager.getCurrentUser().getUserName());
        data.setCreatedTime(new Date());
        data.setLastUpdateBy(data.getCreatedBy());
        data.setLastUpdateTime(data.getCreatedTime());
        if(this.customerService.addCustomer(data)==1){
            return JsonResponse.success("客户添加成功！");
        }else{
            return JsonResponse.fail("客户添加失败！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "remove.do",method = RequestMethod.POST)
    public Object remove(Long id){
        CustomerVo data=new CustomerVo(id);
        data.setLastUpdateBy(TokenManager.getCurrentUser().getUserName());
        data.setLastUpdateTime(new Date());
        if(this.customerService.removeCustomer(data)==1){
            return JsonResponse.success("客户删除成功！");
        }else{
            return JsonResponse.fail("客户删除失败！");
        }
    }
    @ResponseBody
    @RequestMapping(value = "modify.do",method = RequestMethod.POST)
    public Object modify(CustomerVo data){
        data.setCreatedBy(null);
        data.setCreatedTime(null);
        data.setLastUpdateBy(TokenManager.getCurrentUser().getUserName());
        data.setLastUpdateTime(new Date());
        if(this.customerService.modifyCustomer(data)==1){
            return JsonResponse.success("客户修改成功！");
        }else{
            return JsonResponse.fail("客户修改失败！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "info.do/{id}",method = RequestMethod.GET)
    public Object info(@PathVariable Long id){
        CustomerVo data=new CustomerVo(id);
        data.setSuperFlag(TokenManager.getCurrentUser().getSuperFlag());
        data.setUserId(TokenManager.getCurrentUser().getUserId());
        Customer customer=this.customerService.getCustomer(data);
        if(customer!=null){
            return JsonResponse.success("查询成功！",customer);
        }else{
            return JsonResponse.fail("查询失败！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "grid.do",method = RequestMethod.POST)
    public Object grid(CustomerVo data){
        data.setSuperFlag(TokenManager.getCurrentUser().getSuperFlag());
        data.setUserId(TokenManager.getCurrentUser().getUserId());
        Grid<Customer> grid=this.customerService.grid(data);
        if(data.getQueryType()==1){
            if(grid!=null){
                return grid;
            }else{
                return new Grid<Customer>();
            }
        }else{
            if(grid!=null){
                return JsonResponse.success("查询成功！",grid);
            }else{
                return JsonResponse.fail("查询失败！");
            }
        }
    }

    @ResponseBody
    @RequestMapping(value = "easyuiGrid.do",method = RequestMethod.POST)
    public Object easyuiGrid(CustomerVo data){
        data.setSuperFlag(TokenManager.getCurrentUser().getSuperFlag());
        data.setUserId(TokenManager.getCurrentUser().getUserId());
        Grid<Customer> grid=this.customerService.grid(data);
        if(grid!=null){
            return grid;
        }else{
            return new Grid<Customer>();
        }
    }

    @ResponseBody
    @RequestMapping(value = "treeGrid.do",method = RequestMethod.POST)
    public Object treeGrid(CustomerVo data){
        data.setSuperFlag(TokenManager.getCurrentUser().getSuperFlag());
        data.setUserId(TokenManager.getCurrentUser().getUserId());
        List<Customer> list=this.customerService.getCustomers(data);
        List<TreeCustomer>tree=new ArrayList<>();
        if(!list.isEmpty()){
            //1、根节点
            List<Customer> lastCustomer=new ArrayList<>();
            for(Customer customer:list){
                if(customer.getSuperCustId()==null || customer.getCustId()==0){
                    tree.add(this.customer2TreeCustomer(customer));
                }else{
                    lastCustomer.add(customer);
                }
            }


            //2、深度优先递归
            for(TreeCustomer treeCustomer:tree){
                treeCustomer.setChildren(this.tree(treeCustomer.getCustId(),lastCustomer));
            }
        }
        return tree;
    }

    private List<TreeCustomer>tree(Long custId,List<Customer>last){
        List<Customer> lastCustomer=new ArrayList<>();
        List<TreeCustomer>tree=new ArrayList<>();

        for(Customer customer:last){
            if(customer.getSuperCustId().longValue()==custId){
                tree.add(this.customer2TreeCustomer(customer));
            }else {
                lastCustomer.add(customer);
            }
        }

        if(lastCustomer.size()>0){
            for (TreeCustomer treeCustomer:tree){
                treeCustomer.setChildren(this.tree(treeCustomer.getCustId(),last));
            }
            return tree;
        }else{
            return tree;
        }

    }

    private TreeCustomer customer2TreeCustomer(Customer customer){
        if(customer!=null){
            TreeCustomer treeCustomer=new TreeCustomer();
            BeanUtils.copyNotNullProperties(customer,treeCustomer);
            return treeCustomer;
        }
        return null;
    }
}
