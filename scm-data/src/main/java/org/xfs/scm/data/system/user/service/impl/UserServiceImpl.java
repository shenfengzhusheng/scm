package org.xfs.scm.data.system.user.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.github.abel533.mapper.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.common.constant.GlobalConstant;
import org.xfs.scm.common.exception.SqlException;
import org.xfs.scm.common.utils.BeanUtils;
import org.xfs.scm.common.utils.RandomUtils;
import org.xfs.scm.common.utils.encrypt.MD5Util;
import org.xfs.scm.common.utils.encrypt.PasswordHelper;
import org.xfs.scm.common.utils.string.StringUtil;

import com.github.pagehelper.PageHelper;
import org.xfs.scm.data.system.file.po.FilePo;
import org.xfs.scm.data.system.file.service.FileServiceI;
import org.xfs.scm.data.system.file.vo.FileVo;
import org.xfs.scm.data.system.resources.po.ResourcePo;
import org.xfs.scm.data.system.resources.service.ResourcesServiceI;
import org.xfs.scm.data.system.user.entity.User;
import org.xfs.scm.data.system.user.mapper.UserMapper;
import org.xfs.scm.data.system.user.po.UserPo;
import org.xfs.scm.data.system.user.service.UserServiceI;
import org.xfs.scm.data.system.user.vo.UserVo;
import org.xfs.scm.data.system.user_role.service.UserRoleServiceI;
import org.xfs.scm.platform.base.service.impl.BaseServiceImpl;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserServiceI {

	
	@Resource 
	private UserMapper userMapper;
	
	@Resource
	private ResourcesServiceI resourcesService;
	
	@Resource
	private UserRoleServiceI userRoleService;

	@Resource
	private FileServiceI fileService;

	@Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
	public User loginByPc(UserVo vo){
		if(vo!=null && !StringUtil.isEmpty(vo.getUserCode()) && !StringUtil.isEmpty(vo.getPwd())){
			User user=new User();
			BeanUtils.copyNotNullProperties(vo,user);
			return super.queryOne(user);
		}
		return null;
	}

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
				//entity.setResources(resourceList);
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
	public List<UserPo> getUsers(UserVo vo) {

		return this.userMapper.getUsers(vo);
	}

	@Override
	public UserPo getUser(UserVo vo) {
		List<UserPo> list=this.listUser(vo);
		if(!list.isEmpty())
			return list.get(0);
		return null;
	}

	@Override
	public UserPo getUserByUserCode(String userCode) {
		if(!StringUtil.isEmpty(userCode)){
			UserVo userVo=new UserVo();
			userVo.setUserCode(userCode);
			userVo.setSuperFlag(1);
			List<UserPo>list=this.getUsers(userVo);
			if(!list.isEmpty()){
				return list.get(0);
			}
		}
		return null;
	}

	@Override
	public Mapper<User> getMapper() {
		return super.getMapper();
	}

	@Override
	public UserPo getById(Long id) {
		return this.getUser(new UserVo(id));
	}

	@SuppressWarnings("static-access")
	@Override
	public Grid<UserPo> grid(UserVo vo, int page, int rows) {
		Grid<UserPo> grid=new Grid<UserPo>();
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
			String salt= RandomUtils.getMixString(10);
			String pwd=PasswordHelper.getEncryptedPwd("123456", vo.getUserCode() + salt);
			vo.setPwd(pwd);
			vo.setSalt(salt);
			if(this.userMapper.addUser(vo)!=1){
				throw new SqlException("添加用户失败！");
			}
			if(StringUtil.isNotBlank(vo.getHeaderUrl())){
				try{
					new Thread(new Runnable() {
						@Override
						public void run() {
							saveFile(vo.getHeaderUrl(),vo.getUserId());
						}
					}).start();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			return true;
		}
		return false;
	}
	private void saveFile(String headerUrl,Long userId){
		FileVo vo= new FileVo();
		vo.setTableName("tb_user");
		vo.setColumnName("headerUrl");
		vo.setType("jpg");
		vo.setState(GlobalConstant.NORMAL_STATUS);
		vo.setCreatedBy("系统");
		vo.setLastUpdateBy("系统");
		vo.setCreatedTime(new Date());
		vo.setName(headerUrl);
		vo.setLastUpdateTime(new Date());
		vo.setKeyword(userId.toString());
		vo.setUrl(headerUrl);
		this.fileService.addFile(vo);
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Override
	public boolean modifyUser(UserVo vo) {
		if(vo!=null){
			UserVo t=new UserVo();
			String[]ignoreProperties={"userCode","createdBy","createdTime","pwd","salt"};
			BeanUtils.copyNotNullProperties(vo, t,ignoreProperties);
//			if(!StringUtil.isEmpty(vo.getBirthdayText())){
//				try{
//					t.setBirthday(new SimpleDateFormat("yyyy-mm-dd").parse(vo.getBirthdayText()));
//				}catch(Exception e){}
//			}
			if(this.userMapper.modifyUser(vo)!=1){
				throw new SqlException("用户修改失败！");
			}
			if(vo.getHeaderUrl()!=null){
				try{
					new Thread(new Runnable() {
						@Override
						public void run() {
							compareFile(vo);
						}
					}).start();
				}catch (Exception e){
					e.printStackTrace();
				}
			}
			return true;
		}
		return false;
	}
	private void compareFile(UserVo vo){
		FileVo fileVo= new FileVo();
		fileVo.setTableName("tb_user");
		fileVo.setColumnName("headerUrl");
		fileVo.setState(GlobalConstant.NORMAL_STATUS);
		FilePo file=this.fileService.getFile(fileVo);

		if(file!=null){
			if(!file.getColumnName().equalsIgnoreCase(vo.getHeaderUrl())){
				this.deleteFileState(vo.getUserId());
				//无论如何添加一文件记录
				this.saveFile(vo.getHeaderUrl(),vo.getUserId());
			}
		}else{
			//无论如何添加一文件记录
			this.saveFile(vo.getHeaderUrl(),vo.getUserId());
		}


	}


	private void deleteFileState(Long userId){
		FileVo vo= new FileVo();
		vo.setTableName("tb_user");
		vo.setColumnName("headerUrl");
		vo.setState(GlobalConstant.DELETE_STATUS);
		vo.setLastUpdateBy("系统");
		vo.setKeyword(userId.toString());
		vo.setLastUpdateTime(new Date());

		this.fileService.updateFileState(vo);
	}
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Override
	public boolean removeUser(UserVo vo) {
		if(this.deleteById(vo.getUserId())==1){
			if(this.userRoleService.removerUserRoleByUserId(vo.getUserId())){
				try{
					new Thread(new Runnable() {
						@Override
						public void run() {
							deleteFileState(vo.getUserId());
						}
					}).start();
				}catch (Exception e){

				}
				return true;
			}

			return false;
		}
		return false;
	}

	@Override
	public UserPo getLoginUserInfo(UserVo vo) {
		if(vo.getUserCode()!=null){
			List<UserPo>list=this.userMapper.getLoginUserInfo(vo);
			if(!list.isEmpty()){
				return list.get(0);
			}
		}
		return null;
	}

}
