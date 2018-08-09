package org.xfs.scm.system.init.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.xfs.scm.common.utils.encrypt.MD5Util;
import org.xfs.scm.common.utils.string.StringUtil;
import org.xfs.scm.system.dept.entity.Dept;
import org.xfs.scm.system.dept.service.DeptServiceI;
import org.xfs.scm.system.init.service.InitServiceI;
import org.xfs.scm.system.organization.entity.Organization;
import org.xfs.scm.system.organization.service.OrganizationServiceI;
import org.xfs.scm.system.resources.entity.Resources;
import org.xfs.scm.system.resources.service.ResourcesServiceI;
import org.xfs.scm.system.role.entity.Role;
import org.xfs.scm.system.role.service.RoleServiceI;
import org.xfs.scm.system.role_resources.entity.RoleResources;
import org.xfs.scm.system.role_resources.service.RoleResourcesServiceI;
import org.xfs.scm.system.user.entity.User;
import org.xfs.scm.system.user.service.UserServiceI;
import org.xfs.scm.system.user_role.entity.UserRole;
import org.xfs.scm.system.user_role.service.UserRoleServiceI;

@Service
public class InitServiceImpl implements InitServiceI {
	
	private static final Logger logger=LoggerFactory.getLogger(InitServiceImpl.class);
	
	private static final String FILEPATH = "initDataBase.xml";
	private String userName="系统";
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Resource
	private RoleServiceI roleService;
	
	@Resource
	private ResourcesServiceI resourcesService;
	
	@Resource
	private RoleResourcesServiceI roleResourcesService;
	
	@Resource
	private UserServiceI userService;
	
	@Resource
	private UserRoleServiceI userRoleService;
	
	@Resource
	private OrganizationServiceI organizationService;
	
	@Resource
	private DeptServiceI deptService;
	
	@Override
	public void init() {
		try {
			Document document = new SAXReader().read(Thread.currentThread().getContextClassLoader().getResourceAsStream(FILEPATH));

			this.initDb();
			this.initOrganization(document);
			this.initDept(document);
			this.initRole(document);
			this.initUser(document);
			this.initUserRole(document);
			this.initResource(document);
			this.initRoleResource(document);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
	}
	
	/**
	 * 初始化db
	 * project:scm-managers-service
	 * author:xifengshan
	 * date:2017年5月3日下午10:04:57
	 */
	private void initDb(){
	
		this.jdbcTemplate.execute("truncate tb_user");
		this.jdbcTemplate.execute("truncate tb_user_role");
		this.jdbcTemplate.execute("truncate tb_role");
		this.jdbcTemplate.execute("truncate tb_role_resources");
		this.jdbcTemplate.execute("truncate tb_resources");
		this.jdbcTemplate.execute("truncate tb_organization");
		this.jdbcTemplate.execute("truncate tb_dept");

	}
	
	/**
	 * 初始化组织架构
	 * project:scm-managers-service
	 * author:xifengshan
	 * date:2017年5月4日上午6:46:21
	 */
	@SuppressWarnings("rawtypes")
	private void initOrganization(Document document){
		List childNodes = document.selectNodes("//organizations/organization");
		for(Object obj:childNodes){
			Node node=(Node)obj;
			Organization o=new Organization();
			o.setOcode(node.valueOf("@code"));
			o.setOname(node.valueOf("@name"));
			o.setState(Byte.valueOf("1"));
			o.setCreatedBy(this.userName);
			o.setLastUpdateBy(o.getCreatedBy());
			o.setIndependence(Byte.valueOf("1"));
			o.setMemo("总公司");
			o.setAddress(node.valueOf("@address"));
			if(!StringUtil.isEmpty(node.valueOf("@poid"))){
				o.setPoid(Long.parseLong(node.valueOf("@poid")));
			}
			logger.info(this.organizationService.saveSelective(o)+"");
		}
	}
	
	
	/**
	 * 初始化部门
	 * project:scm-managers-service
	 * @param document
	 * author:xifengshan
	 * date:2017年5月4日上午8:20:38
	 */
	private void initDept(Document document){
		@SuppressWarnings("rawtypes")
		List childNodes = document.selectNodes("//depts/dept");
		for(Object obj:childNodes){
			Node node=(Node)obj;
			Dept d=new Dept();
			d.setDeptCode(node.valueOf("@code"));
			d.setDeptName(node.valueOf("@name"));
			d.setOid(1L);
			d.setState(Byte.valueOf("1"));
			d.setCtype(Byte.valueOf("2"));
			d.setCreatedBy(this.userName);
			d.setLastUpdateBy(this.userName);
			logger.info(this.deptService.saveSelective(d)+"");
		}

	}

	/**
	 * 初始化角色
	 * project:scm-managers-service
	 * @param document
	 * author:xifengshan
	 * date:2017年5月4日上午9:03:21
	 */
	private void initRole(Document document){
		@SuppressWarnings("rawtypes")
		List childNodes = document.selectNodes("//roles/role");
		int seq=1;
		for(Object obj:childNodes){
			Node node=(Node)obj;
			Role r=new Role();
			r.setDeptId(1L);
			r.setSeq(seq++);
			r.setRname(node.valueOf("@name"));
			r.setMemo(node.valueOf("@memo"));
			r.setState(Byte.valueOf("1"));
			r.setCreatedBy(this.userName);
			r.setLastUpdateBy(this.userName);
			
			logger.info(this.roleService.saveSelective(r)+"");
		}

	}
	/**
	 * 初始化用户
	 * project:scm-managers-service
	 * @param document
	 * author:xifengshan
	 * date:2017年5月4日上午9:00:27
	 */
	private void initUser(Document document){
		@SuppressWarnings("rawtypes")
		List childNodes = document.selectNodes("//users/user");
		for(Object obj:childNodes){
			Node node=(Node)obj;
			User u=new User();
			u.setBirthday(new Date());
			u.setUserCode(node.valueOf("@userCode"));
			u.setUserName(node.valueOf("@userName"));
			u.setPwd(MD5Util.md5(node.valueOf("@pwd")));
			u.setDeptId(1L);
			u.setOid(1L);
			u.setState(Byte.valueOf("1"));
			u.setSex(Integer.parseInt(node.valueOf("@sex")));
			u.setCreatedBy(this.userName);
			u.setLastUpdateBy(this.userName);
			logger.info(this.userService.saveSelective(u)+"");
		}

	}
	
	/**
	 * 初始化用户角色
	 * project:scm-managers-service
	 * @param document
	 * author:xifengshan
	 * date:2017年5月4日上午9:14:11
	 */
	@SuppressWarnings({ "unchecked"})
	private void initUserRole(Document document){
		@SuppressWarnings("rawtypes")
		List childNodes = document.selectNodes("//users_roles/user");
		for(Object obj:childNodes){
			Node node=(Node)obj;
		
			List<Node> cNodes = node.selectNodes("role");
			for(Node n:cNodes){
				UserRole ur=new UserRole();
				ur.setUserId(Long.parseLong(node.valueOf("@id")));
				ur.setRid(Integer.parseInt(n.valueOf("@id")));
				logger.info(this.userRoleService.saveSelective(ur)+"");

			}
		//	ur.setRid(Integer.parseInt(node.valueOf("@id")));
		}

	}
	
	/**
	 * 初始化资源
	 * project:scm-managers-service
	 * @param document
	 * author:xifengshan
	 * date:2017年5月4日上午9:31:20
	 */
	private void initResource(Document document){
		@SuppressWarnings("rawtypes")
		List childNodes = document.selectNodes("//resources/resource");
		for(Object obj:childNodes){
			Node node=(Node)obj;
			Resources r=new Resources();
			r.setRsid(node.valueOf("@id"));
			r.setName(node.valueOf("@name"));
			r.setUrl(node.valueOf("@url"));
			r.setIconcls(node.valueOf("@iconCls"));
			if(!StringUtil.isBlank(node.valueOf("@pid"))){
				r.setPid(node.valueOf("@pid"));
			}
			r.setSeq(Integer.parseInt(node.valueOf("@seq")));
			if (!StringUtil.isBlank(node.valueOf("@target"))) {
				r.setTarget(node.valueOf("@target"));
			} else {
				r.setTarget("");
			}
			Node n = node.selectSingleNode("resourcetype");
			if(n.valueOf("@id").equals("0")){
				r.setRsType("Menu");
			}else{
				r.setRsType("function");

			}
			
			r.setCreatedBy(this.userName);
			r.setLastUpdateBy(this.userName);
			logger.info(this.resourcesService.saveSelective(r)+"");
		}

	}
	
	/**
	 * 初始化角色资源
	 * project:scm-managers-service
	 * @param document
	 * author:xifengshan
	 * date:2017年5月4日上午9:31:20
	 */
	@SuppressWarnings("unchecked")
	private void initRoleResource(Document document){
		@SuppressWarnings("rawtypes")
		List childNodes = document.selectNodes("//roles_resources/role");
		for(Object obj:childNodes){
			Node node=(Node)obj;
			List<Node> cNodes = node.selectNodes("resource");
			for (Node n : cNodes) {
				RoleResources r=new RoleResources();
				r.setRid(Integer.parseInt(node.valueOf("@id")));
				r.setRsid(n.valueOf("@id"));
				logger.info(this.roleResourcesService.saveSelective(r)+"");

			}
		}

	}
	
}
