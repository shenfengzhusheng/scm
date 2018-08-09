package org.xfs.scm.platform.init;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.xfs.scm.platform.config.data.db.DataSourceConfig;
import org.xfs.scm.platform.config.data.mongo.MongoConfig;
import org.xfs.scm.platform.config.dubbo.DubboConsumerConfig;
import org.xfs.scm.platform.config.dubbo.DubboProviderConfig;
import org.xfs.scm.platform.config.mq.active.ActiveMqConfig;
import org.xfs.scm.platform.config.quartz.SchedulerConfig;
import org.xfs.scm.platform.config.redis.RedisConfig;
import org.xfs.scm.platform.config.shiro.ShiroConfig;
import org.xfs.scm.platform.config.spring.SpringRootConfig;
import org.xfs.scm.platform.config.spring.SpringWebConfig;
import org.xfs.scm.platform.config.websocket.SocketConfig;

import javax.servlet.*;
import java.util.EnumSet;


/**
 * web服务初始化
 * Created by 神风逐胜 on 2017/10/7 0007.20:27
 * version:1.0
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    private static final Logger logger= LoggerFactory.getLogger(AppInitializer.class);
    public void onStartup(ServletContext servletContext) throws ServletException {
        if(logger.isDebugEnabled()){
           logger.debug( "启动加载内容！");
        }
       // servletContext.addListener(MyServletContextAttributeListener.class);//添加session监听
        //servletContext.addListener(MyRequestAndSessionAttributeListener.class);
     //   servletContext.addListener(NettyListener.class);//启动netty
        FilterRegistration.Dynamic shiroFilter =servletContext.addFilter("shiroFilter", DelegatingFilterProxy.class);
        shiroFilter.setInitParameter("targetFilterLifecycle", "true");
        shiroFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, "/*");
        super.onStartup(servletContext);
//        try {
//            new NettyServerBootstrap(10000);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        //   this.registerDispatcherServlet(servletContext);
    }
    /**
     *  获取服务配置信息 junit也是这引入这些类
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{SpringRootConfig.class,DataSourceConfig.class,ActiveMqConfig.class, MongoConfig.class,SocketConfig.class,RedisConfig.class,ShiroConfig.class,SchedulerConfig.class};
    }

    /**
     * spring mvc 配置
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{SpringWebConfig.class,DubboConsumerConfig.class};
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

       // delegatingFilterProxy.set
        return new Filter[]{characterEncodingFilter};
    }

}
