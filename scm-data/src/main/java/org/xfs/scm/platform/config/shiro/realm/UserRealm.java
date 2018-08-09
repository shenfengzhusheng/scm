package org.xfs.scm.platform.config.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xfs.scm.data.system.resources.entity.Resources;
import org.xfs.scm.data.system.role.entity.Role;
import org.xfs.scm.data.system.user.po.UserPo;
import org.xfs.scm.data.system.user.service.UserServiceI;

import java.util.HashSet;
import java.util.Set;


public class UserRealm extends AuthorizingRealm {
    private static final Logger logger= LoggerFactory.getLogger(UserRealm.class);
    // 用户对应的角色信息与权限信息都保存在数据库中，通过UserService获取数据
    private UserServiceI userService;
    public UserServiceI getUserService() {
        return userService;
    }
    public void setUserService(UserServiceI userService) {
        this.userService = userService;
    }
    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// String userCode=(String)principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		// 从token中获取用户权限，即菜单信息
		try {
			UserPo po = TokenManager.getCurrentUser();
			// 用户角色
			Set<String> roles = new HashSet<String>();
			for (Role role : po.getRoles()) {
				roles.add(role.getRname());
			}

			authorizationInfo.setRoles(roles);
			// 用户菜单

			Set<String> authoritys = new HashSet<String>();
			for (Resources resources : po.getResources()) {
				authoritys.add(resources.getUrl());
			}
			authorizationInfo.setStringPermissions(authoritys);
			return authorizationInfo;
		} catch (Exception e) {
			logger.error("获取token异常！");

		}

		return null;
    }

    /**
     * realm的认证方法，从数据库查询用户信息
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userCode=((UsernamePasswordToken)token).getUsername();
        char[] chars = ((UsernamePasswordToken) token).getPassword();
        String password=new String(chars);

        UserPo userPo=this.userService.getUserByUserCode(userCode);

        if(userPo==null){
            throw new UnknownAccountException("账号不存,请核对清楚重新输入!");
        }
        if(userPo.getState()!=1){
            throw new UnknownAccountException("账号已被禁用!");
        }
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userPo.getUserCode(), //用户名
                userPo.getPwd(), //密码
                new SimpleByteSource(userCode+userPo.getSalt()),//salt
                getName()  //realm name
        );
        return authenticationInfo;
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }
}
