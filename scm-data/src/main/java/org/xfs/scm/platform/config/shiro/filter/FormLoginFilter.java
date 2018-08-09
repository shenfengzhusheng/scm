package org.xfs.scm.platform.config.shiro.filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xfs.scm.common.base.model.JsonResponse;
import org.xfs.scm.data.system.resources.entity.Resources;
import org.xfs.scm.data.system.user.po.UserPo;
import org.xfs.scm.data.system.user.service.UserServiceI;
import org.xfs.scm.data.system.user.vo.UserVo;
import org.xfs.scm.platform.config.shiro.realm.TokenManager;
import org.xfs.scm.platform.util.json.FastJsonUtil;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FormLoginFilter extends PathMatchingFilter {
    private static final Logger logger= LoggerFactory.getLogger(FormLoginFilter.class);
    private String loginUrl = "/user/login";
    private String successUrl = "/main";
    private UserServiceI userService;


    public FormLoginFilter(){}
    public FormLoginFilter( UserServiceI userService){
        this.userService=userService;
    }

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        if(SecurityUtils.getSubject().isAuthenticated()) {
            return true;//已经登录过
        }
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        if(isLoginRequest(httpServletRequest)) {
            if("post".equalsIgnoreCase(httpServletRequest.getMethod())) {//form表单提交
                boolean loginSuccess = login(httpServletRequest); //登录
                if(loginSuccess) {

                    PrintWriter printWriter= httpServletResponse.getWriter();
                    printWriter.write(FastJsonUtil.toJsonString(JsonResponse.success("登陆成功！")));
                    printWriter.close();
                    return redirectToSuccessUrl(httpServletRequest, httpServletResponse);
                }
            }
            return true;//继续过滤器链
        } else {//保存当前地址并重定向到登录界面
            saveRequestAndRedirectToLogin(httpServletRequest, httpServletResponse);
            return false;
        }
    }
    private void saveRequestAndRedirectToLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebUtils.saveRequest(req);
        WebUtils.issueRedirect(req, resp, "/login");
    }

    private boolean login(HttpServletRequest req) {
        String username = req.getParameter("userCode");
        String password = req.getParameter("pwd");

        try {
            UsernamePasswordToken token=new UsernamePasswordToken(username, password);
            token.setRememberMe(true);

            SecurityUtils.getSubject().login(token);
            if(TokenManager.isLogin()){
                UserVo data=new UserVo();
                data.setUserCode(username);
                UserPo user=userService.getLoginUserInfo(data);
                if(user!=null){
                    Resources resources=new Resources();
                    resources.setUrl("/currentUser.do");
                    if(user.getResources().isEmpty()){
                        List<Resources> list=new ArrayList<Resources>();

                        list.add(resources);
                        user.setResources(list);
                    }else{
                        user.getResources().add(resources);
                    }
                    if(user.getUserId().longValue()==1){
                        user.setSuperFlag(1);
                    }
                    TokenManager.setValToSession("login_user",user);

                }

                logger.debug("登陆成功,sessionId:[{}]",TokenManager.getSession().getId());
                // return "redirect:/main"; //登录成功
            }
        } catch (Exception e) {
            req.setAttribute("shiroLoginFailure", e.getClass());
            return false;
        }
        return true;
    }

    private boolean redirectToSuccessUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("ccd");
        //获取用户未登录之前的地址
        String oldUrl = (String) request.getAttribute(WebUtils.FORWARD_REQUEST_URI_ATTRIBUTE);
        String forwardUrl=successUrl;
        if(oldUrl!=null){
            forwardUrl=oldUrl;
        }
        WebUtils.redirectToSavedRequest(request, response, forwardUrl);
        return false;
    }

    private boolean isLoginRequest(HttpServletRequest req) {
        return pathsMatch(loginUrl, WebUtils.getPathWithinApplication(req));
    }
}
