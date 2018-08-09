package org.xfs.scm.system.organization.web;

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
import org.xfs.scm.system.organization.entity.Organization;
import org.xfs.scm.system.organization.po.OrganizationPo;
import org.xfs.scm.system.organization.service.OrganizationServiceI;
import org.xfs.scm.system.organization.vo.OrganizationVo;
import org.xfs.scm.util.BeanUtils;

@Controller
@RequestMapping("/rest/sys/organization/")
public class OrganizationWeb extends BaseWeb {
	
	@Autowired
	private OrganizationServiceI organizationService;

	@RequestMapping("/grid.do")
	@ResponseBody
	public Grid grid(OrganizationVo vo, int page, int rows)throws Exception{
		SessionInfo sessionInfo = (SessionInfo) this.getSession().getAttribute(ConfigUtil.getSessionInfoName());
		if(sessionInfo!=null){
			for(String str:sessionInfo.getResourceList()){
				if(str.contains("organization")){
					System.out.println(str);
				}
			}
		}
		Grid grid=this.organizationService.grid(vo, page, rows);
		return grid;
	}
	
	@RequestMapping("/quickSearch.do")
	@ResponseBody
	public Grid quickSearch(OrganizationVo vo,int page,int rows)throws Exception{
		Grid grid=this.organizationService.grid(vo, page, rows);
		return grid;
	}
	
	@RequestMapping("/info.do")
	@ResponseBody
	public Object getById(Long id)throws Exception{
		OrganizationPo op=this.organizationService.getById(id);
		return op;
	}
	
	
	
	@RequestMapping("/save.do")
	@ResponseBody
	public Object save(OrganizationVo data)throws Exception{
		Json json=new Json();
		boolean flag=false;
		SessionInfo sessionInfo = (SessionInfo) this.getSession().getAttribute(ConfigUtil.getSessionInfoName());
		data.setLastUpdateBy(sessionInfo.getUserName());
		data.setCreatedBy(sessionInfo.getUserName());
		if(this.organizationService.addOrganization(data)){
			flag=true;
		}
		json.setMsg(flag?"添加成功！":"添加失败！");
		json.setSuccess(flag);
		return json;
	}
	
	@RequestMapping("/modify.do")
	@ResponseBody
	public Object update(OrganizationVo data)throws Exception{
		Json json=new Json();
		boolean flag=false;
		SessionInfo sessionInfo = (SessionInfo)  this.getSession().getAttribute(ConfigUtil.getSessionInfoName());
		data.setLastUpdateBy(sessionInfo.getUserName());
		if(data.getOid()!=0){
			Organization entity=new Organization();
			BeanUtils.copyNotNullProperties(data, entity);
			if(this.organizationService.updateByIdSelective(entity)==1){
				flag=true;
			}
		}
	
		json.setMsg(flag?"修改成功！":"修改失败！");
		json.setSuccess(flag);
		return json;
	}
	
	@RequestMapping("/delete.do")
	@ResponseBody
	public Object remove( Long id)throws Exception{
		Json json=new Json();
		boolean flag=false;
		if(this.organizationService.deleteById((Object)id)==1){
			flag=true;
		}
		json.setMsg(flag?"删除组织成功！":"删除组织失败！");
		json.setSuccess(flag);
		return json;
	}
	
//	@ResponseBody
//	@RequestMapping("/getPName")
//	public List<Tree>getMainMenu(OrganizationPo data,HttpSession session){
//		List<OrganizationPo> o = this.organizationService.getPName(data);
//		List<Tree> tree = new ArrayList<Tree>();
//		for (OrganizationPo op : o) {
//			if(op.getOid()==null){
//				Tree node = new Tree();
//				BeanUtils.copyNotNullProperties(op, node);
//				node.setText(op.getOname());
//				Map<String, String> attributes = new HashMap<String, String>();
//				attributes.put("pname", op.getPname());
//				attributes.put("oname", op.getOname());
//				node.setAttributes(attributes);
//				tree.add(node);
//			}
//		}
//		return tree;
//	}
	@RequestMapping("getPName.do")
	@ResponseBody
	public Object getPName(OrganizationVo vo)throws Exception{
		SessionInfo sessionInfo = (SessionInfo) this.getSession().getAttribute(ConfigUtil.getSessionInfoName());
		List<OrganizationPo>organization=this.organizationService.listOrganization(vo);
		if(sessionInfo!=null){
		}
		return organization;
	}

	@RequestMapping("getOrganization.do")
	@ResponseBody
	public Object getOrganization(OrganizationVo vo)throws Exception{
		SessionInfo sessionInfo = (SessionInfo) this.getSession().getAttribute(ConfigUtil.getSessionInfoName());
		if(sessionInfo!=null){
			return this.organizationService.listOrganization(vo);
		}
		return null;
	}
	
	
}
