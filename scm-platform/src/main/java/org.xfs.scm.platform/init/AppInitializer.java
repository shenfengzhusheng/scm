package org.xfs.scm.platform.init;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.xfs.scm.platform.config.data.db.DataSourceConfig;
import org.xfs.scm.platform.config.data.mongo.MongoConfig;
import org.xfs.scm.platform.config.spring.SpringRootConfig;
import org.xfs.scm.platform.config.spring.SpringWebConfig;
import org.xfs.scm.platform.config.websocket.SocketConfig;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;


/**
 * web服务初始化
 * Created by 神风逐胜 on 2017/10/7 0007.20:27
 * version:1.0
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.out.println("启动加载内容！");
       // servletContext.addListener(MyServletContextAttributeListener.class);//添加session监听
        //servletContext.addListener(MyRequestAndSessionAttributeListener.class);
        super.onStartup(servletContext);
     //   this.registerDispatcherServlet(servletContext);
    }
    /**
     *  获取服务配置信息 junit也是这引入这些类
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{SpringRootConfig.class,DataSourceConfig.class, MongoConfig.class,SocketConfig.class};
    }

    /**
     * spring mvc 配置
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{SpringWebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }


    /**
     * 编码过滤器
     * @return
     */
    @Override
    protected Filter[] getServletFilters(){
        CharacterEncodingFilter characterEncodingFilter=new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return new Filter[]{characterEncodingFilter};
    }

}
