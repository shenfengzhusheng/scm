package org.xfs.scm.platform.config.filter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import com.alibaba.druid.support.http.WebStatFilter;

/**
 * Servlet Filter implementation class DruidStatFilter
 */
@WebFilter(filterName="druidFilter", urlPatterns="/*", initParams={
		@WebInitParam(name="exclusions", value="*.js,*.gif,*.jpg,*.png,*.css,*.jsp,*.ico,/druid/*")
},asyncSupported = true)
public class DruidStatFilter extends WebStatFilter {


}
