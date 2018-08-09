package org.xfs.scm.data.system.user_role.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.xfs.scm.data.system.user_role.entity.UserRole;
import org.xfs.scm.data.system.user_role.mapper.UserRoleMapper;
import org.xfs.scm.data.system.user_role.service.UserRoleServiceI;
import org.xfs.scm.platform.base.service.impl.NBaseServiceImpl;


@Service
public class UserRoleServiceImpl extends NBaseServiceImpl<UserRole> implements UserRoleServiceI {
	
	@Resource 
	private UserRoleMapper userRoleMapper;
	
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	@Override
	public List<UserRole> getUserRole(UserRole vo) {
		return this.userRoleMapper.getUserRole(vo);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Override
	public boolean removeUserRole(UserRole vo) {
		if(vo!=null){
			this.userRoleMapper.removeRole(vo);
			return true;
		}
		return false;
	}


	@Override
	public boolean removeUserRoleByRid(Integer rid) {
		UserRole ur=new UserRole();
		ur.setRid(rid);
		return this.removeUserRole(ur);
	}

	@Override
	public boolean removerUserRoleByUserId(Long userId) {
		UserRole ur=new UserRole();
		ur.setUserId(userId);
		return this.removeUserRole(ur);
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Override
	public boolean grantRole(Long id, List<Integer> ids) {
		UserRole ur=new UserRole();
		ur.setUserId(id);
		List<UserRole>list=this.userRoleMapper.getUserRole(ur);
		
		List<Integer>newList=new ArrayList<Integer>();
		List<Object>deleteList=new ArrayList<Object>();
		
		
		//删除用户被删除角色的角色
		for(UserRole userRole:list){
			boolean deleteFlag=true;
			for(int i=0,size=ids.size();i<size;i++){
				if(userRole.getRid().toString().equals(ids.get(i))){
					deleteFlag=false;
					break;
				}
			}
			if(deleteFlag){
				deleteList.add(userRole.getId());
			}
			
		}
		
		if(!deleteList.isEmpty()){
	       int deleteCount= this.deleteByIds(deleteList);
	       System.out.println(deleteList.size()+"--------------"+deleteCount);
		}
		
		//添加新用户新添角色
		for(int i=0,size=ids.size();i<size;i++){
			boolean newFlag=true;
			for(UserRole userRole:list){
				if(userRole.getRid().toString().equals(ids.get(i))){
					newFlag=false;
					break;
				}
			}
			if(newFlag){
				newList.add(ids.get(i));
			}
		}
		
		if(!newList.isEmpty()){
			List<UserRole>addList=new ArrayList<UserRole>();
			for(Integer rid:newList){
				UserRole r=new UserRole();
				r.setUserId(id);
				r.setRid(rid);
				addList.add(r);
			}
			int addCount=this.userRoleMapper.addUserRole(addList);
			System.out.println(addList.size()+"addCount:"+addCount);

		}
		
		return true;
	}

}
