package org.xfs.scm.platform.config.shiro.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.xfs.scm.data.system.user.po.UserPo;

/**
 * Created by 神风逐胜 on 2018/2/4 0004.21:22
 * version:1.0
 */
public class TokenManager {

    /**
     * 这是在用户登录成功后添加到session里面的
     * @return
     */
    public static UserPo getCurrentUser(){
        UserPo user=(UserPo)getValFromSession("login_user");
        return user;
    }

    /**
     * 获取当前登录的用户名
     * @return
     */
    public static String getUserName(){
        String userName=SecurityUtils.getSubject().getPrincipal().toString();
        return userName;
    }

    /**
     * 获取当前用户的Session
     * @return
     */
    public static Session getSession(){
        return SecurityUtils.getSubject().getSession();
    }

    /**
     * 从当前登录用户的Session里取值
     * @param key
     * @return
     */
    public static Object getValFromSession(Object key){
        return getSession().getAttribute(key);
    }

    /**
     * 把值放入到当前登录用户的Session里
     * @param key
     * @param value
     */
    public static void setValToSession(Object key,Object value){
        getSession().setAttribute(key,value);
    }

    /**
     * 用户登录
     * @param userName
     * @param password
     * @param rememberMe
     */
    public static void login(String userName,String password,Boolean rememberMe){
        //创建用户token
        UsernamePasswordToken token=new UsernamePasswordToken(userName,password);
        token.setRememberMe(rememberMe);//是否记住我
        //用户登录
        SecurityUtils.getSubject().login(token);
    }

    /**
     * 判断用户是否已经登录
     *
     * @return
     */
    public static boolean isLogin() {
        return null != SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 退出登录
     */
    public static void logout() {
        SecurityUtils.getSubject().logout();

    }

}
