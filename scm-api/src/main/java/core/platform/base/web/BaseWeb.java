package core.platform.base.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/baseweb/")
public class BaseWeb {
	
	
	/**
	 * 获取request
	 * project:fm-core
	 * @return
	 * author:xifengshan
	 * date:2017年8月27日下午10:05:33
	 */
	protected HttpServletRequest getRequest(){
		HttpServletRequest request=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}
	
	/**
	 * 获取session
	 * project:fm-core
	 * @return
	 * author:xifengshan
	 * date:2017年8月27日下午10:07:29
	 */
	protected HttpSession getSession(){
		return this.getRequest().getSession();
	}
}
