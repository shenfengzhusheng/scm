package org.xfs.scm.platform.exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.xfs.scm.common.exception.BusinessException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Jeken.Liu
 *
 */
public class MyExceptionHandler implements HandlerExceptionResolver  {
	
	private final static Logger logger =LoggerFactory.getLogger(MyExceptionHandler.class);
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		logger.info("---------------  MyExceptionHandler----------------------->"+(ex.getClass()==BusinessException.class));
//		if(request.getContentType().equalsIgnoreCase("")){
//			
//		}
		return null;
//		ModelAndView modelAndView = new ModelAndView("errorPage");
//	    return modelAndView;
	}

}
