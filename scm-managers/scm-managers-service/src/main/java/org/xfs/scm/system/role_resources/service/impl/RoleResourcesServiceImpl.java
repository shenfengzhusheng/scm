package org.xfs.scm.system.role_resources.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.xfs.scm.base.service.impl.NBaseServiceImpl;
import org.xfs.scm.common.exception.BusinessException;
import org.xfs.scm.common.utils.string.StringUtil;
import org.xfs.scm.system.role_resources.dao.RoleResourcesMapper;
import org.xfs.scm.system.role_resources.entity.RoleResources;
import org.xfs.scm.system.role_resources.service.RoleResourcesServiceI;
import org.xfs.scm.system.role_resources.vo.RoleResourcesVo;


@Service
public class RoleResourcesServiceImpl extends NBaseServiceImpl<RoleResources> implements RoleResourcesServiceI {
	//private static final Logger logger=LoggerFactory.getLogger(RoleResourcesServiceImpl.class);
	
	@Resource
	private RoleResourcesMapper roleResourcesMappper;
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=BusinessException.class)
	@Override
	public boolean grant(Integer id, String ids)throws BusinessException {
		if(!StringUtil.isBlank(ids)){
			String rsids[]=ids.split(",");
			RoleResourcesVo vo=new RoleResourcesVo();
			vo.setRid(id);
			List<RoleResources>list=this.roleResourcesMappper.getRoleResources(vo);
			
			//新添角色资源
			List<RoleResourcesVo>addList=new ArrayList<RoleResourcesVo>();
			List<Object> deleteIds=new ArrayList<Object>();
			//原来角色资源
			List<String>oldIds=new ArrayList<String>();
			List<String>newIds=new ArrayList<String>();

			for(int i=0;i<list.size();i++){
				oldIds.add(list.get(i).getRsid());
			}
			for(int i=0;i<rsids.length;i++){
				newIds.add(rsids[i]);
			}
			
			System.out.println(oldIds);
//			System.out.println("--------------------------->");
			System.out.println(newIds);
			for(int i=0;i<rsids.length;i++){
				if(!oldIds.contains(rsids[i])){//新添加权限
					 RoleResourcesVo rs=new RoleResourcesVo();
					 rs.setRid(id);
					 rs.setRsid(rsids[i]);
					 addList.add(rs);
				}				
			}
			if(!addList.isEmpty()){
				int succsCount=this.roleResourcesMappper.addRoleResources(addList);
				System.out.println(addList.size()+"succsCount:"+succsCount);
			}
			for(int i=0;i<list.size();i++){
				Boolean flag=newIds.contains(list.get(i).getRsid().toString());
				Boolean oldFlag=oldIds.contains(list.get(i).getRsid().toString());
				if(!flag&& oldFlag){//删除权限
					deleteIds.add(list.get(i).getId());
					System.out.println(list.get(i).getId());
				}
				
			}
			if(!deleteIds.isEmpty()){
				int deleteCount=this.deleteByIds(deleteIds);
				System.out.println(deleteIds.size()+"[deleteCount]:"+deleteCount);
			}

			return true;
		}
		return false;
	}

	

}
