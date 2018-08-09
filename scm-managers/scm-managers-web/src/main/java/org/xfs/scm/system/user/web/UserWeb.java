package org.xfs.scm.system.user.web;

import java.text.ParseException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.xfs.scm.base.web.BaseWeb;
import org.xfs.scm.common.base.model.Json;
import org.xfs.scm.common.session.SessionInfo;
import org.xfs.scm.common.utils.AESUtil;
import org.xfs.scm.common.utils.IpUtil;
import org.xfs.scm.common.utils.encrypt.MD5Util;
import org.xfs.scm.common.utils.string.StringUtil;
import org.xfs.scm.platform.util.ConfigUtil;
import org.xfs.scm.system.user.po.UserPo;
import org.xfs.scm.system.user.service.UserServiceI;
import org.xfs.scm.system.user.vo.UserVo;
import org.xfs.scm.system.user_role.service.UserRoleServiceI;
import org.xfs.scm.util.BeanUtils;

@Controller
@RequestMapping("/rest/sys/user")
public class UserWeb extends BaseWeb{

	@Resource
	private UserServiceI userService;
	@Resource
	private UserRoleServiceI userRoleService;
	
	@ResponseBody
	@RequestMapping(value="/login.do")
	public Json login(UserVo vo)throws Exception{
		Json json=new Json();
		if(vo==null){
			json.setMsg("数据异常！");
		}else if(StringUtil.isEmpty(vo.getUserCode())){
			json.setMsg("帐号不能为空！");
		}else if(StringUtil.isEmpty(vo.getPwd())){
			json.setMsg("密码不能为空!");
		}else{
			String pwd = AESUtil.Decrypt(vo.getPwd(), "0102030405060708");
			vo.setPwd(pwd);
			UserPo po=this.userService.login(vo);
			if(po!=null){
				SessionInfo sessionInfo = new SessionInfo();
				
				BeanUtils.copyProperties(po, sessionInfo);
				sessionInfo.setUserId(po.getUserId());
				sessionInfo.setIp(IpUtil.getIpAddr(this.getRequest()));
				if(po.getUserId().longValue()==1){
					sessionInfo.setSuperFlag(1);
				}
				//sessionInfo.setResourceList(u.getResourceList());
			//	sessionInfo.setToken(uab.getAccess_token());
				
				this.getSession().setAttribute(ConfigUtil.getSessionInfoName(), sessionInfo);
			
				json.setData(po);
				json.setSuccess(true);
			}else
				json.setMsg("用户名或密码错误！");
		}
		
		return json;
	}
	@ResponseBody
	@RequestMapping("/logout")
	public Object logout(){
		Json json = new Json();
		if (this.getSession() != null) {
			this.getSession() .invalidate();
		}
		json.setSuccess(true);
		json.setMsg("注销成功！");
		return json;
	}
	
	@ResponseBody
	@RequestMapping("/grid.do")
	public Object grid(UserVo vo,int page,int rows){
		return this.userService.grid(vo,page,rows);
	}
	
	@ResponseBody
	@RequestMapping("/quickSearch")
	public Object quickSearch(UserVo vo,int page,int rows){
		return this.userService.grid(vo,page,rows);
	}
	
	@ResponseBody
	@RequestMapping("/info.do")
	public Object getById(Long id){
		return this.userService.getById(id);
	}
	
	@RequestMapping("/save.do")
	@ResponseBody
	public Object save(UserVo data)throws Exception{
		Json json=new Json();
		boolean flag=false;
		if(data !=null){
			data.setPwd(MD5Util.md5("123456"));//设置默认密码为123456
			try {
				if(!StringUtil.isEmpty(data.getBirthdayText())){
					data.setBirthday(new java.text.SimpleDateFormat("yyyy-MM-dd").parse(data.getBirthdayText()));
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if(this.userService.addUser(data)){
				flag=true;
			}
		}
		json.setMsg(flag?"添加用户成功！":"添加用户失败！");
		json.setSuccess(flag);
		return json;
	}
	@RequestMapping("/modify.do")
	@ResponseBody
	public Object modify(UserVo data)throws Exception{
		Json json=new Json();
		boolean flag=false;
		if(data.getUserId()!=0){
			try {
				if(!StringUtil.isEmpty(data.getBirthdayText())){
					data.setBirthday(new java.text.SimpleDateFormat("yyyy-MM-dd").parse(data.getBirthdayText()));
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}		
			if(this.userService.modifyUser(data)){
				flag=true;
			}
		}
	
		json.setMsg(flag?"修改用户成功！":"修改用户失败！");
		json.setSuccess(flag);
		return json;
	}
	
	@RequestMapping("/delete.do")
	@ResponseBody
	public Object delete(UserVo data)throws Exception{
		Json json=new Json();
		boolean flag=false;
		if(this.userService.removeUser(data)){
			flag=true;
		}
		json.setMsg(flag?"删除用户成功！":"删除用户失败！");
		json.setSuccess(flag);
		return json;
	}
	
	@RequestMapping("/updatePassword")
	@ResponseBody//	
	public Object updatePassword(UserVo data)throws Exception{
		Json json=new Json();
		boolean flag=false;
		SessionInfo sessionInfo = (SessionInfo) this.getSession().getAttribute(ConfigUtil.getSessionInfoName());
		if(sessionInfo.getUserId()!=0){
			data.setPwd(MD5Util.md5(data.getPwd()));
			data.setUserId(sessionInfo.getUserId());
			if(this.userService.modifyUser(data)){
				flag=true;
			}
		}
		json.setMsg(flag?"修改密码成功！":"修改密码失败！");
		json.setSuccess(flag);
		return json;
	}
	
	@RequestMapping("/grantRole")
	@ResponseBody//	
	public Object grantRole(Long id,String ids){
		Json json=new Json();
		boolean flag=false;
		String msg="用户授权角色失败！";
		if(id!=null && !StringUtil.isEmpty(ids)){
			try {
				flag=this.userRoleService.grantRole(id, ids);
				
			} catch (Exception e) {
				msg=e.getMessage();
			}
		}
		json.setSuccess(flag);
		json.setMsg(flag?"用户授权角色成功！":msg);
		return json;
	}
	
}
