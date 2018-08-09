package org.xfs.scm.platform.config.init;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.xfs.scm.platform.config.cache.redis.CacheConfig;
import org.xfs.scm.platform.config.db.DataSourceConfig;
import org.xfs.scm.platform.config.security.SecurityConfig;
import org.xfs.scm.platform.config.spring.SpringRootConfig;
import org.xfs.scm.platform.config.spring.SpringWebConfig;


import javax.servlet.Filter;


/**
 * 
 * @author Jeken.Liu
 *
 */


public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * 获取配置信息 junit也是这引入这些类
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {SpringRootConfig.class, DataSourceConfig.class, CacheConfig.class, SecurityConfig.class};
    }


    /**
     * spring mvc 配置
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {SpringWebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }


    /**
     * 过滤器
     */
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return new Filter[] {characterEncodingFilter};
    }

    // @Override
    // public void onStartup(ServletContext servletContext) throws ServletException {
    //
    // super.onStartup(servletContext);
    //
    // //servletContext.setInitParameter(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "dev");
    // }
}
