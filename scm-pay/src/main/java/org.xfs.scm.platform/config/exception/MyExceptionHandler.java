package org.xfs.scm.platform.config.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.xfs.scm.common.base.model.JsonResponse;
import org.xfs.scm.platform.util.http.RequestInfoUtil;
import org.xfs.scm.platform.util.json.FastJsonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
		if(logger.isDebugEnabled()){
			logger.debug("---------------  MyExceptionHandler----------------------->"+(ex.getClass()==BusinessException.class));
			logger.debug(RequestInfoUtil.getRequestHeaders(request).toString());
		}
		ModelAndView mv = null;
		// JSP格式返回

		if(!(request.getHeader("accept").contains("application/json")  || (request.getHeader("X-Requested-With")!= null && request.getHeader("X-Requested-With").contains("XMLHttpRequest") ))) {
			mv=new ModelAndView();
			String errorMessage= FastJsonUtil.toJsonString(new JsonResponse<String>(ex.getMessage()));

			mv.setViewName("/error/400");
			mv.addObject("errorInfo",errorMessage);
		}else{// JSON格式返回
			jsonResponse(response,ex);
		}
		return mv;
	}

	private void jsonResponse(HttpServletResponse response, Exception ex){
		String errorMessage= FastJsonUtil.toJsonString(new JsonResponse<String>(ex.getMessage()));
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			response.getWriter().write(errorMessage);
		} catch (IOException e) {
			logger.error("convert json error!");
		}
	}

}
