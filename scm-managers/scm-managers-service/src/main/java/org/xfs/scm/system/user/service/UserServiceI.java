package org.xfs.scm.system.user.service;

import java.util.List;

import org.xfs.scm.base.service.BaseServiceI;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.system.user.entity.User;
import org.xfs.scm.system.user.po.UserPo;
import org.xfs.scm.system.user.vo.UserVo;

public interface UserServiceI extends BaseServiceI<User> {
	/**
	 * 用户登陆
	 * project:scm-managers-service
	 * @param vo
	 * @return
	 * author:xifengshan
	 * date:2017年5月3日下午1:19:27
	 */
	UserPo login(UserVo vo);
	
	
	
	List<UserPo> listUser(UserVo vo);
	
	UserPo getUser(UserVo vo);
	UserPo getById(Long id);
	
	Grid grid(UserVo vo, int page, int rows);
	
	boolean addUser(UserVo vo);
	
	boolean modifyUser(UserVo vo);

	
	boolean removeUser(UserVo vo);
	
}
