package org.xfs.scm.data.system.dept.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xfs.scm.common.base.model.JsonResponse;
import org.xfs.scm.data.system.dept.service.DeptServiceI;
import org.xfs.scm.data.system.dept.vo.DeptVo;
import org.xfs.scm.platform.base.web.BaseWeb;

import javax.annotation.Resource;

@Controller
@RequestMapping("/sys/dept/")
public class DeptWeb  extends BaseWeb{

    @Resource
    private DeptServiceI deptService;


    @ResponseBody
    @RequestMapping(value = "modify",method = RequestMethod.GET)
    public Object modify(DeptVo data){
        if(this.deptService.updateByIdSelective(data)==1){
            return JsonResponse.success("修改成功！",data);
        }else{
            return JsonResponse.fail("修改失败！");
        }
    }
}
