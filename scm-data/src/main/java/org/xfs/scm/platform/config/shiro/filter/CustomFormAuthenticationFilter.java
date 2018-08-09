package org.xfs.scm.platform.config.shiro.filter;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {
    private static final Logger logger = LoggerFactory.getLogger(CustomFormAuthenticationFilter.class);//定义日志输出
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject,
                                     ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        issueSuccessRedirect(request, response);

      //  WebUtils.getAndClearSavedRequest(request);
      //  WebUtils.redirectToSavedRequest(request,response,"/main#/sys/map");
        return false;
    }

    @Override
    protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {

        SavedRequest savedRequest = WebUtils.getSavedRequest(request);
        String superURL = null;
        if(savedRequest!=null){
            superURL = savedRequest.getRequestUrl();
            if(superURL==null||"".equals(superURL)) {
                superURL = getSuccessUrl();
            }
            HttpServletRequest httpRequest = WebUtils.toHttp(request);

            String forwardUrl=(String)httpRequest.getSession().getAttribute("forwardUrl");
            System.out.println("forwardUrl:"+forwardUrl);
//            }else if(superURL.contains("/CBSP")){
//                superURL = superURL.replace("/CBSP", "");
//            }

        }else{
            superURL = getSuccessUrl();
        }
        WebUtils.issueRedirect(request, response, superURL, null, true);

    }

    /**
     * 退出时使用
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean isLoginSubmission(ServletRequest request, ServletResponse response) {
        return (request instanceof HttpServletRequest) && WebUtils.toHttp(request).getMethod().equalsIgnoreCase(POST_METHOD);
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        AuthenticationToken token = createToken(request, response);
        if (token == null) {
            String msg = "createToken method implementation returned null. A valid non-null AuthenticationToken " +
                    "must be created in order to execute a login attempt.";
            throw new IllegalStateException(msg);
        }
        try {
            HttpServletRequest httpRequest = WebUtils.toHttp(request);

            Subject subject = getSubject(request, response);
            String userName=(String)httpRequest.getAttribute("userCode");
            String pwd=(String)httpRequest.getAttribute("pwd");

            subject.login(token);
            return onLoginSuccess(token, subject, request, response);
        } catch (AuthenticationException e) {
            return onLoginFailure(token, e, request, response);
        }
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                if (logger.isTraceEnabled()) {
                    logger.trace("Login submission detected.  Attempting to execute login.");
                }
                return executeLogin(request, response);
            } else {
                if (logger.isTraceEnabled()) {
                    logger.trace("Login page view.");
                }
                //allow them to see the login page ;)
                return true;
            }
        } else {
            if (logger.isTraceEnabled()) {
                logger.trace("Attempting to access a path which requires authentication.  Forwarding to the " +
                        "Authentication url [" + getLoginUrl() + "]");
            }
            HttpServletRequest httpRequest = WebUtils.toHttp(request);
            String method=httpRequest.getMethod();//之前的请求方法
            String query=httpRequest.getQueryString();//之前请求的条件
            String requestUri=httpRequest.getRequestURI();//之前请求的路径
            StringBuffer requestURL=httpRequest.getRequestURL();//之前请求的全路径
            // 组装URL
            StringBuilder returnUrl = new StringBuilder("http://"+httpRequest.getServerName()+":"+httpRequest.getServerPort());
            returnUrl.append(httpRequest.getRequestURI());
            if(requestUri.indexOf("/login")<0){
                HttpSession session=httpRequest.getSession();
                session.setAttribute("forwardUrl",requestUri);
            }
            System.out.println("returnUrl is:"+returnUrl);
            saveRequestAndRedirectToLogin(request, response);
            return false;
        }
    }
}
