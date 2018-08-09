package org.xfs.scm.data.system.user.service;

import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.data.system.user.entity.User;
import org.xfs.scm.data.system.user.po.UserPo;
import org.xfs.scm.data.system.user.vo.UserVo;
import org.xfs.scm.platform.base.service.BaseServiceI;

import java.util.List;



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

	User loginByPc(UserVo vo);
	
	List<UserPo> listUser(UserVo vo);

	List<UserPo>getUsers(UserVo vo);

	UserPo getUser(UserVo vo);

	UserPo getUserByUserCode(String userCode);

	UserPo getById(Long id);
	
	Grid<UserPo> grid(UserVo vo, int page, int rows);
	
	boolean addUser(UserVo vo);
	
	boolean modifyUser(UserVo vo);

	
	boolean removeUser(UserVo vo);

	UserPo getLoginUserInfo(UserVo vo);
	
}
