package org.xfs.scm.system.resource.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.xfs.scm.base.web.BaseWeb;
import org.xfs.scm.common.base.model.Json;
import org.xfs.scm.common.base.model.Tree;
import org.xfs.scm.common.session.SessionInfo;
import org.xfs.scm.common.utils.string.StringUtil;
import org.xfs.scm.platform.util.ConfigUtil;
import org.xfs.scm.system.resources.entity.Resources;
import org.xfs.scm.system.resources.po.ResourcePo;
import org.xfs.scm.system.resources.service.ResourcesServiceI;
import org.xfs.scm.system.resources.vo.ResourcesVo;
import org.xfs.scm.util.BeanUtils;


@Controller
@RequestMapping("/rest/sys/resource")
public class ResourcesWeb extends BaseWeb {
	
	@Resource
	private ResourcesServiceI resourcesService;
	
	@RequestMapping("/treeGrid")
	@ResponseBody
	public List<ResourcePo> treeGrid()throws Exception{
		List<ResourcePo> list=new ArrayList<ResourcePo>();
		SessionInfo sessionInfo = (SessionInfo) this.getSession().getAttribute(ConfigUtil.getSessionInfoName());
		if(sessionInfo!=null){
			//UserRole vo=new UserRole();
			ResourcesVo vo=new ResourcesVo();
			vo.setUserId(sessionInfo.getUserId());
			vo.setSuperFlag(sessionInfo.getSuperFlag());
			vo.setUserId(sessionInfo.getUserId());
			list=this.resourcesService.getResources(vo);
		}
		return list;
	}
	
	@ResponseBody
	@RequestMapping("/getMenu.do")
	public List<Tree>getMenu()throws Exception{
		SessionInfo sessionInfo = (SessionInfo) this.getSession().getAttribute(ConfigUtil.getSessionInfoName());
		List<Tree> tree = new ArrayList<Tree>();

		if(sessionInfo!=null){

			ResourcesVo vo=new ResourcesVo();
			vo.setSuperFlag(sessionInfo.getSuperFlag());
			vo.setUserId(sessionInfo.getUserId());
			List<ResourcePo> resources = this.resourcesService.getResources(vo);
			if(resources!=null){
				for (ResourcePo resource : resources) {
					if(resource.getRsType()!=null && resource.getRsType().equalsIgnoreCase("Menu")){
						Tree node = new Tree();
						node.setId(resource.getId());
						node.setIconCls(resource.getIconCls());
						node.setPid(resource.getPid());
						node.setText(resource.getName());
					//	node.setState(resource.get);
						Map<String, String> attributes = new HashMap<String, String>();
						attributes.put("url", resource.getUrl());
						attributes.put("target", resource.getTarget());
					
						node.setAttributes(attributes);
						tree.add(node);
					}
				}
			
			}
			
		}
		
		return tree;
	}
	
	@ResponseBody
	@RequestMapping("/info.do")
	public Object info(String id){
		return this.resourcesService.getById(id);
	}
	

	@ResponseBody
	@RequestMapping("/getMainMenu")
	public List<Tree>getMainMenu(ResourcesVo vo){
		List<ResourcePo> resources=new ArrayList<ResourcePo>();
		SessionInfo sessionInfo = (SessionInfo) this.getSession().getAttribute(ConfigUtil.getSessionInfoName());
		if(sessionInfo!=null){
			vo.setSuperFlag(sessionInfo.getSuperFlag());
			vo.setUserId(sessionInfo.getUserId());
			resources=this.resourcesService.getResources(vo);
		}
		List<Tree> tree = new ArrayList<Tree>();
		for (ResourcePo resource : resources) {
			if(resource.getRsType().equalsIgnoreCase("Menu")){
				Tree node = new Tree();
				BeanUtils.copyNotNullProperties(resource, node);
				node.setText(resource.getName());
				Map<String, String> attributes = new HashMap<String, String>();
				attributes.put("url", resource.getUrl());
				attributes.put("target", resource.getTarget());
				node.setAttributes(attributes);
				tree.add(node);
			}
		}
		return tree;
	}
	
	
	@RequestMapping("/add.do")
	@ResponseBody
	public Object add(Resources data)throws Exception{
		Json json=new Json();
		boolean flag=false;
		String msg="添加资源失败！";
		data.setRsid(UUID.randomUUID().toString().replaceAll("-", ""));
		if(this.resourcesService.saveSelective(data)==1){
			flag=true;
		}
		json.setMsg(flag?"添加资源成功！":msg);
		json.setSuccess(flag);
		return json;
	}
	
	@RequestMapping("/modify.do")
	@ResponseBody
	public Object modify(Resources data)throws Exception{
		Json json=new Json();
		boolean flag=false;
		String msg="修改资源失败！";

		if(data.getRsid().equals(data.getPid())){
			msg="不能将上级资源设为自己！";
		}else{

			if(this.resourcesService.updateByIdSelective(data)==1){
				flag=true;
			}
		}
		json.setMsg(flag?"修改资源成功！":msg);
		json.setSuccess(flag);
		return json;
	}
	
	@RequestMapping("/delete.do")
	@ResponseBody
	public Object delete(String id)throws Exception{
		Json json=new Json();
		if(!StringUtil.isEmpty(id)){
			if(this.resourcesService.removeResources(id)){
				json.setSuccess(true);
				json.setMsg("删除资源成功！");
			}else{
				json.setMsg("删除资源失败！");
			}
		}else{
			json.setMsg("请选择要删除的资源！");
		}
		return json;
	}	
	
	
	@RequestMapping("/getRoleResources")
	@ResponseBody
	public Object getRoleResources(Integer id){
		Json json=new Json();
		boolean flag=false;
		String msg="查询用户权限失败！";
		try{
			
			SessionInfo sessionInfo = (SessionInfo) this.getSession().getAttribute(ConfigUtil.getSessionInfoName());
			if(sessionInfo!=null){
				List<ResourcePo> list=this.resourcesService.getRoleResources(id);
				if(list!=null && list.size()>0){
					json.setData(list);
					flag=true;
					msg="查询成功！";
				}else{
					msg="该角色暂无权限！";
				}
			}
		}catch(Exception e){
			msg=e.getMessage();
		}
		json.setMsg(msg);
		json.setSuccess(flag);
		return json;
	}
	
	
}
