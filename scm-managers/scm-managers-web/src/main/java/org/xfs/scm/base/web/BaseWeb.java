package org.xfs.scm.base.web;

import java.text.SimpleDateFormat;
import java.util.Date;



import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.xfs.scm.platform.util.StringEscapeEditor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/baseController")
public class BaseWeb {
	
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder){
	   // System.out.println("------------------DemoBaseController.initBinder()---------------------");
		/**
		 * 自动转换日期类型的字段格式
		 */
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
		
		/**
		 * 防止XSS攻击
		 */
		binder.registerCustomEditor(String.class, new StringEscapeEditor(true,false));
	}
	/**获取request对象
	 */
	protected HttpServletRequest getRequest(){
		HttpServletRequest request =  ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();    
		return request;
	}
	/**获取response对象
	 */
	protected HttpServletResponse getResponse(){
		//HttpServletResponse response=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest()
		return null;
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
	

	/**
	 * 用户跳转JSP页面
	 * 
	 * 此方法不考虑权限控制
	 * 
	 * @param folder
	 *            路径
	 * @param jspName
	 *            JSP名称(不加后缀)
	 * @return 指定JSP页面
	 */
	@RequestMapping("/{folder}/{jspName}")
	public String redirectJsp(@PathVariable String folder, @PathVariable String jspName) {
		return "/" + folder + "/" + jspName;
	}
}
