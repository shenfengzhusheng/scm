package org.xfs.scm.system.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.xfs.scm.base.service.impl.BaseServiceImpl;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.common.utils.encrypt.MD5Util;
import org.xfs.scm.common.utils.string.StringUtil;
import org.xfs.scm.system.resources.po.ResourcePo;
import org.xfs.scm.system.resources.service.ResourcesServiceI;
import org.xfs.scm.system.user.dao.UserMapper;
import org.xfs.scm.system.user.entity.User;
import org.xfs.scm.system.user.po.UserPo;
import org.xfs.scm.system.user.service.UserServiceI;
import org.xfs.scm.system.user.vo.UserVo;
import org.xfs.scm.system.user_role.service.UserRoleServiceI;
import org.xfs.scm.util.BeanUtils;

import com.github.pagehelper.PageHelper;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserServiceI {

	
	@Resource 
	private UserMapper userMapper;
	
	@Resource
	private ResourcesServiceI resourcesService;
	
	@Resource
	private UserRoleServiceI userRoleService;
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.NOT_SUPPORTED)
	public UserPo login(UserVo vo) {
		if(vo!=null && !StringUtil.isEmpty(vo.getUserCode()) && !StringUtil.isEmpty(vo.getPwd())){
			vo.setPwd(MD5Util.md5(vo.getPwd()));
			vo.setState(Byte.valueOf("1"));
			List<UserPo>list=this.userMapper.getUser(vo);
			if(!list.isEmpty()){
				UserPo po=list.get(0);
				List<ResourcePo>resources=this.resourcesService.getUserResources(po.getUserId());
				List<String>resourceList=new ArrayList<String>();
				for(ResourcePo p:resources){
					resourceList.add(p.getUrl());
				}
				po.setResourceList(resourceList);
				return po;
			}
		}
		return null;
	}

	@Transactional(readOnly=false,propagation=Propagation.NOT_SUPPORTED)
	@Override
	public List<UserPo> listUser(UserVo vo) {
		return this.userMapper.getUser(vo);
	}

	@Override
	public UserPo getUser(UserVo vo) {
		List<UserPo> list=this.listUser(vo);
		if(!list.isEmpty())
			return list.get(0);
		return null;
	}

	@Override
	public UserPo getById(Long id) {
		return this.getUser(new UserVo(id));
	}

	@SuppressWarnings("static-access")
	@Override
	public Grid grid(UserVo vo, int page, int rows) {
		Grid grid=new Grid();
		PageHelper pageHelper=new PageHelper();
		pageHelper.startPage(page, rows, false);
		grid.setRows(this.userMapper.getUser(vo));
		grid.setTotal(this.userMapper.countUser(vo));
		return grid;
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Override
	public boolean addUser(UserVo vo) {
		if(vo!=null){
			User t=new User();
			BeanUtils.copyNotNullProperties(vo, t);
			t.setUserId(null);
			if(this.saveSelective(t)==1){
				return true;
			}
			return false;
		}
		return false;
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Override
	public boolean modifyUser(UserVo vo) {
		if(vo!=null){
			User t=new User();
			BeanUtils.copyNotNullProperties(vo, t);
			if(this.updateByIdSelective(t)==1){
				return true;
			}
			return false;
		}
		return false;
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Override
	public boolean removeUser(UserVo vo) {
		if(this.deleteById(vo.getUserId())==1){
			if(this.userRoleService.removerUserRoleByUserId(vo.getUserId()))
				return true;
			return false;
		}
		return false;
	}

}
