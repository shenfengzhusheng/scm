package org.xfs.scm.system.role.web;

import java.util.Collections;
import java.util.Comparator;
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
import org.xfs.scm.system.role.po.RolePo;
import org.xfs.scm.system.role.po.TreeRole;
import org.xfs.scm.system.role.service.RoleServiceI;
import org.xfs.scm.system.role.vo.RoleVo;
import org.xfs.scm.system.role_resources.service.RoleResourcesServiceI;

@Controller
@RequestMapping("/rest/sys/role")
public class RoleWeb extends BaseWeb {
	
	@Autowired
	private RoleServiceI roleService;
	
	@Autowired
	private RoleResourcesServiceI roleResourcesService;
	
	@RequestMapping("/grid.do")
	@ResponseBody
	public Object grid(RoleVo vo,int page,int rows){
		SessionInfo sessionInfo =(SessionInfo)this.getSession().getAttribute(ConfigUtil.getSessionInfoName());
		Grid grid=new Grid();

		if(sessionInfo!=null){
			vo.setUserId(sessionInfo.getUserId());
			vo.setSuperFlag(sessionInfo.getSuperFlag());
			grid=this.roleService.gridRole(vo, page, rows);
		}
		return grid;
	}
	
	@RequestMapping("/info.do")
	@ResponseBody
	public Object getById(Integer id){
		RolePo role=this.roleService.getById(id);
		return role;
	}
	

	@RequestMapping("/save.do")
	@ResponseBody
	public Object save(RoleVo data)throws Exception{
		Json json=new Json();
		boolean flag=false;
		String msg="添加角色失败！";
		if(this.roleService.addRole(data)){
			flag=true;
		}
		json.setMsg(flag?"添加角色成功！":msg);
		json.setSuccess(flag);
		return json;
	}
	
	@RequestMapping("/grant.do")
	@ResponseBody
	public Object grant(Integer id,String ids){
		Json json=new Json();
		boolean flag=false;
		String msg="角色赋权失败！";
		try{
			flag=this.roleResourcesService.grant(id, ids);
		}catch(Exception e){
			msg=e.getMessage();
		}
		json.setMsg(flag?"角色赋权成功！":msg);
		json.setSuccess(flag);
		return json;
	}
	
	@RequestMapping("/modify.do")
	@ResponseBody
	public Object modify(RoleVo data)throws Exception{
		Json json=new Json();
		boolean flag=false;
		String msg="修改角色失败！";
		if(this.roleService.modfiyRole(data)){
			flag=true;
		}
		json.setMsg(flag?"修改角色成功！":msg);
		json.setSuccess(flag);
		return json;
	}
	
	@RequestMapping("/delete.do")
	@ResponseBody
	public Object delete(Integer id)throws Exception{
		Json json=new Json();
		boolean flag=false;
		if(this.roleService.removeRoles(id)){
			flag=true;
		}
		json.setMsg(flag?"删除角色成功！":"删除角色失败！");
		json.setSuccess(flag);
		return json;
	}
	
	@RequestMapping("/getRolesTree.do")
	@ResponseBody
	public Object getRolesTree(){
		SessionInfo sessionInfo = (SessionInfo) this.getSession().getAttribute(ConfigUtil.getSessionInfoName());
		if(sessionInfo!=null){
			RoleVo vo=new RoleVo();
			vo.setSuperFlag(sessionInfo.getSuperFlag());
			vo.setUserId(sessionInfo.getUserId());
			List<TreeRole>roles=this.roleService.treeRole(vo);
			Collections.sort(roles, new Comparator<TreeRole>() {// 排序
				@Override
				public int compare(TreeRole o1, TreeRole o2) {
					if (o1.getSeq() == null) {
						o1.setSeq(1000);
					}
					if (o2.getSeq() == null) {
						o2.setSeq(1000);
					}
					return o1.getSeq().compareTo(o2.getSeq());
				}
			});
			return roles;
		}else{
			return  null;
		}
		
	}
	
	
	@RequestMapping("/getRoleByUserId.do")
	@ResponseBody
	public Object getRoleByUserId(String id)throws Exception{
		RoleVo vo=new RoleVo();
		vo.setUserId(Long.parseLong(id));
		List<TreeRole>roles=this.roleService.treeRole(vo);
		
		return roles;
	}
	
		
}
