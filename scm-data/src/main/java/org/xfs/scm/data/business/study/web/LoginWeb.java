package org.xfs.scm.data.business.study.web;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.xfs.scm.common.base.model.JsonResponse;
import org.xfs.scm.data.system.resources.entity.Resources;
import org.xfs.scm.data.system.user.po.UserPo;
import org.xfs.scm.data.system.user.service.UserServiceI;
import org.xfs.scm.data.system.user.vo.UserVo;
import org.xfs.scm.platform.base.web.BaseWeb;
import org.xfs.scm.platform.config.shiro.realm.TokenManager;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller()
@RequestMapping("/")
public class LoginWeb extends BaseWeb {
    private final static Logger logger= LoggerFactory.getLogger(LoginWeb.class);

    @Resource
    private UserServiceI userService;

    @RequestMapping("job")
    public String job(Map<String, Object> model) {
        return "/job/JobManager";
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){
        if(logger.isDebugEnabled()) {
            //logger.debug("------------------------------------->login page!");
        }
        return  "redirect:/main";
    }

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public ModelAndView login(){

        if(logger.isDebugEnabled()) {
          //  logger.debug("------------------------------------->login page!");
        }
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("login");
        return  modelAndView;
    }

    @RequestMapping(value = "/upload",method = RequestMethod.GET)
    public String upload(){
        if(logger.isDebugEnabled()) {
            //logger.debug("------------------------------------->login page!");
        }
        return  "/upload";
    }


    @ResponseBody
    @RequestMapping(value = "/user/login")
    public Map<String,Object> userLogin(UserVo data, ModelMap modelMap){
        String forwardUrl="login";
        if(logger.isDebugEnabled()){
         //   logger.debug("userName;"+vo.getUserCode()+"     ,pwd:"+vo.getPwd());
        }
        String msg="";
        System.out.println(super.getRequest().getRequestURI()+" request url:"+ super.getRequest().getRequestURL());
        if(data.getUserCode()!=null && data.getUserCode()!="" && data.getPwd()!=null && data.getPwd()!=""){
            try{
                TokenManager.login(data.getUserCode(),data.getPwd(),true);
                if(TokenManager.isLogin()){

                    UserPo user=this.userService.getLoginUserInfo(data);
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
                  //  WebUtils.getSavedRequest(super.getRequest()).getRequestUrl();
                    //WebUtils.getAndClearSavedRequest(super.getRequest());
                    //获取用户未登录之前的地址
                    String url = (String) super.getRequest().getAttribute(WebUtils.FORWARD_REQUEST_URI_ATTRIBUTE);
                    if(url==null){
                        forwardUrl="main";
                    }else{
                        forwardUrl=url;
                    }
                    System.out.println("old request url is:"+url);
                    logger.debug("登陆成功,sessionId:[{}]",TokenManager.getSession().getId());
                   // return "redirect:/main"; //登录成功
                }
            }catch (IncorrectCredentialsException e) {
                msg = "登录密码错误. ";

            } catch (ExcessiveAttemptsException e) {
                msg = "登录失败次数过多,请在15分钟后重试！";
            } catch (LockedAccountException e) {
                msg = "帐号已被锁定. ";

            } catch (DisabledAccountException e) {
                msg = "帐号已被禁用.";

            } catch (ExpiredCredentialsException e) {
                msg = "帐号已过期.";
            } catch (UnknownAccountException e) {
                msg = "帐号不存在.";

            } catch (UnauthorizedException e) {
                msg = "您没有得到相应的授权！";
            }


        }else{
            msg="用户名密码不能不为空！";
        }
        if(msg.equals("")){
            modelMap.put("code","100");
        }else{
            modelMap.put("code","400");
        }
        modelMap.addAttribute("message",msg);
        modelMap.put("back_url",forwardUrl);
        return modelMap;
    }
//    @RequestMapping(value = "login",method = RequestMethod.GET)
//    public String login(String userName,String password){
//        if(logger.isDebugEnabled()){
//            logger.debug("userName;"+userName+"     ,password:"+password);
//        }
//        return "redirect:/main";
//    }
    @RequiresPermissions("/currentUser.do")
    @RequestMapping(value = "/main")
    public String main(){
        if(logger.isDebugEnabled()){
           // logger.debug("forward to here !");
        }
        UserPo po= TokenManager.getCurrentUser();
        return "main";
    }

    @RequestMapping("/refuse")
    public String refuse(){
        return "error/refuse";
    }

    @RequestMapping("/download")
    public String download(){
        return "download";
    }
    @RequestMapping(value = "views/tables",method = RequestMethod.GET)
    public String tables(){
        return "tables";
    }

    @RequestMapping(value = "views/dashboard",method = RequestMethod.GET)
    public String datasgboard(){
        return "common/dashboard";
    }

    @RequiresPermissions("/currentUser.do")
    @ResponseBody
    @RequestMapping(value = "/currentUser.do",method = RequestMethod.GET)
    public Object currentUser(){
        UserPo po= TokenManager.getCurrentUser();
        if(po!=null){
            return JsonResponse.success("获取成功！",po);
        }else{
            return JsonResponse.fail("获取失败！");
        }
    }



    @RequiresPermissions("/permissions.do")
    @ResponseBody
    @RequestMapping(value = "/permissions.do",method = RequestMethod.GET)
    public Object permissions(){
        UserPo po= TokenManager.getCurrentUser();
        if(po!=null){
            return JsonResponse.success("获取成功！",po);
        }else{
            return JsonResponse.fail("获取失败！");
        }
    }

    @RequestMapping("/logout.do")
    public Object logout(){
        TokenManager.logout();
        return JsonResponse.success("退出登陆！");
    }
    @RequestMapping("/download.do")
    public String dowoload(){
        return "download";
    }

    /**
     * 文件下载
     * @param response
     */
    @RequestMapping("/dowoloadFile.do")
    public void dowoloadFile(HttpServletResponse response){

        response.setContentType("application/x-download");
        String filedownload = "d://qhtshipper-release.apk";
        String filedisplay = "货主apk";
        OutputStream outp = null;
        FileInputStream in = null;
        try {
            filedisplay = URLEncoder.encode(filedisplay, "UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + filedisplay);
            outp = response.getOutputStream();
            in = new FileInputStream(filedownload);
            byte[] b = new byte[1024];
            int i = 0;
            while ((i = in.read(b)) > 0) {
                outp.write(b, 0, i);
            }
            outp.flush();
        } catch (UnsupportedEncodingException el) {
           logger.error("文件下载编码异常！",el);
        }catch (Exception e) {

        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                in = null;
            }
            if (outp != null) {
                    try {
                    outp.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                outp = null;
            }
        }

    }

    @RequestMapping(value = "error-500")
    public String error_500(){
        return "/error/error-500";
    }
}
