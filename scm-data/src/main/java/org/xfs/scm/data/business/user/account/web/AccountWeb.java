package org.xfs.scm.data.business.user.account.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.common.base.model.JsonResponse;
import org.xfs.scm.data.business.user.account.service.AccountServiceI;
import org.xfs.scm.data.business.user.account.vo.AccountVo;
import org.xfs.scm.data.business.user.shipper.shipper.bo.ShipperBo;
import org.xfs.scm.data.business.user.shipper.shipper.service.ShipperServiceI;
import org.xfs.scm.data.business.user.shipper.shipper.vo.ShipperVo;
import org.xfs.scm.platform.base.web.BaseWeb;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user/account/")
public class AccountWeb extends BaseWeb {

    @SuppressWarnings("unused")
	private static final Logger logger= LoggerFactory.getLogger(AccountWeb.class);

    @Resource
    private AccountServiceI accountService;

    @Resource
    private ShipperServiceI shipperService;

    @ResponseBody
    @RequestMapping(value = "grid",method = RequestMethod.POST)
    public Object grid(AccountVo data,int page,int rows){
        Grid<AccountVo> grid=this.accountService.gridAccount(data,page,rows);
        if(grid!=null){
            return JsonResponse.success("查询成功！",grid);
        }else{
            return JsonResponse.fail("查询失败！");
        }
    }

    @RequestMapping(value = "info.do",method = RequestMethod.POST)
    @ResponseBody
    public Object info(Long id,int type){
        if(type==1){
            ShipperVo shipperVo=new ShipperVo();
            shipperVo.setUserId(id);
            ShipperBo shipperBo=this.shipperService.getShipper(shipperVo);
            if(shipperBo!=null){
                JsonResponse.success("获取成功！",shipperBo);
            }
        }else{

        }
        return JsonResponse.fail("获取帐户信息失败！");
    }


}
