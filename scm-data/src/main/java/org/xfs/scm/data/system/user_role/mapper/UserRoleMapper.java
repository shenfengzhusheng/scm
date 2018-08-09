package org.xfs.scm.data.system.user_role.mapper;

import java.util.List;


import com.github.abel533.mapper.Mapper;
import org.xfs.scm.data.system.user_role.entity.UserRole;

public interface UserRoleMapper extends Mapper<UserRole>{
   
	int addUserRole(List<UserRole> list);
	
	List<UserRole> getUserRole(UserRole vo);
	
	int removeRole(UserRole vo);
}