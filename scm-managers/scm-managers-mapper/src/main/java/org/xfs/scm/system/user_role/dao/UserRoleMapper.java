package org.xfs.scm.system.user_role.dao;

import java.util.List;

import org.xfs.scm.system.user_role.entity.UserRole;

import com.github.abel533.mapper.Mapper;

public interface UserRoleMapper extends Mapper<UserRole>{
   
	int addUserRole(List<UserRole> list);
	
	List<UserRole> getUserRole(UserRole vo);
	
	int removeRole(UserRole vo);
}