package org.xfs.scm.system.dept.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.xfs.scm.base.web.BaseWeb;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.common.base.model.Json;
import org.xfs.scm.common.session.SessionInfo;
import org.xfs.scm.platform.util.ConfigUtil;
import org.xfs.scm.system.dept.po.DeptPo;
import org.xfs.scm.system.dept.service.DeptServiceI;
import org.xfs.scm.system.dept.vo.DeptVo;

@Controller
@RequestMapping("/rest/sys/dept")
public class DeptWeb extends BaseWeb {
	
	@Autowired
	private DeptServiceI deptService;
	
	@RequestMapping("/grid.do")
	@ResponseBody
	public Grid grid(DeptVo vo,int page,int rows){
		Grid grid=this.deptService.grid(vo,page,rows);
		return grid;
	}
	
	@RequestMapping("/quickSearch.do")
	@ResponseBody
	public Grid quickSearch(DeptVo vo,int page,int rows){
		Grid grid=this.deptService.grid(vo,page,rows);
		return grid;
	}
	
	@RequestMapping("/info.do")
	@ResponseBody
	public Object getById(Long id){
		DeptPo dp=this.deptService.getById(id);
		return dp;
	}
	
	
	
	@RequestMapping("/save.do")
	@ResponseBody
	public Object save(DeptVo vo){
		Json json=new Json();
		boolean flag=false;
		SessionInfo sessionInfo = (SessionInfo) this.getSession().getAttribute(ConfigUtil.getSessionInfoName());
		vo.setLastUpdateBy(sessionInfo.getUserName());
		vo.setCreatedBy(sessionInfo.getUserName());
		if(this.deptService.addDept(vo)){
			flag=true;
		}
		json.setMsg(flag?"添加成功！":"添加失败！");
		json.setSuccess(flag);
		return json;
	}
	
	@RequestMapping("/modify.do")
	@ResponseBody
	public Object modify(DeptVo vo){
		Json json=new Json();
		boolean flag=false;
		SessionInfo sessionInfo = (SessionInfo) this.getSession().getAttribute(ConfigUtil.getSessionInfoName());
		vo.setLastUpdateBy(sessionInfo.getUserName());
		if(vo.getDeptId()!=0){
			if(this.deptService.modifyDept(vo)){
				flag=true;
			}
		}
	
		json.setMsg(flag?"修改成功！":"修改失败！");
		json.setSuccess(flag);
		return json;
	}
	
	@RequestMapping("/delete.do")
	@ResponseBody
	public Object delete(DeptVo vo){
		Json json=new Json();
		boolean flag=false;
		if(this.deptService.removeDept(vo)){
			flag=true;
		}
		json.setMsg(flag?"删除网点成功！":"删除网点失败！");
		json.setSuccess(flag);
		return json;
	}
	
	@RequestMapping("/getDept.do")
	@ResponseBody
	public Object getDept(DeptVo vo){
		SessionInfo sessionInfo = (SessionInfo) this.getSession().getAttribute(ConfigUtil.getSessionInfoName());
		if(sessionInfo!=null){
			List<DeptPo>depts=this.deptService.list(vo);

			return depts;
		}
		return "[]";
	}
	
/*	
	@RequestMapping("grid")
	@ResponseBody
	public Object grid(DeptVo vo,HttpSession session){
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Grid grid=new Grid();

		if(sessionInfo!=null){
		//	vo.setUserId(sessionInfo.getUserId());
			grid.setRows(this.deptService.getDept(vo));
			
		//	grid.setTotal(this.deptService.count(vo));
		}
		return grid;
	}
	

	
	@RequestMapping("/getById")
	@ResponseBody
	public Object getById(String id,HttpSession session){
		Dept role=this.deptService.getById(id);
		return role;
	}
	

	@RequestMapping("/add")
	@ResponseBody
	public Object add(Dept data,HttpSession session){
		Json json=new Json();
		boolean flag=false;
		String msg="添加角色失败！";
		if(this.deptService.save(data)){
			flag=true;
		}
		json.setMsg(flag?"添加角色成功！":msg);
		json.setSuccess(flag);
		return json;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Object update(Dept data,HttpSession session){
		Json json=new Json();
		boolean flag=false;
		String msg="修改角色失败！";
		
		if(this.deptService.update(data)){
			flag=true;
		}
		json.setMsg(flag?"修改角色成功！":msg);
		json.setSuccess(flag);
		return json;
	}
	
	@RequestMapping("/remove")
	@ResponseBody
	public Object remove(String id,HttpSession session){
		Json json=new Json();
		boolean flag=false;
		Dept entity=new Dept();
		entity.setDeptId(Long.parseLong(id));
		if(this.deptService.delete(entity)){
			flag=true;
		}
		json.setMsg(flag?"删除角色成功！":"删除角色失败！");
		json.setSuccess(flag);
		return json;
	}*/
}
