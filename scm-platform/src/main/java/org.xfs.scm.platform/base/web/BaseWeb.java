package org.xfs.scm.platform.base.web;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.xfs.scm.common.session.SessionInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/base/baseController")
public class BaseWeb {

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder){
        /**
         * 自动转换日期类型的字段格式
         */
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));

    }

    /**获取request对象
     */
    protected HttpServletRequest getRequest(){
        HttpServletRequest request =  ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }
    /**
     * 获取session
     * @auto:神风逐胜
     * @mail:1294686247@qq.com
     * @project:wms
     * @version:1.0
     * @datetime:2016年3月24日上午10:16:20
     * @return
     */
    protected HttpSession getSession(){
        return  ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
    }


    protected String getUserName(){
        SessionInfo sessionInfo = (SessionInfo)  this.getSession().getAttribute("sessionInfo");
        return sessionInfo.getUserName();
    }
}
