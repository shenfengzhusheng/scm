package org.xfs.scm.platform.config.spring;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.Lists;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.xfs.scm.platform.config.exception.MyExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by 神风逐胜 on 2017/10/7 0007.20:15
 * version:1.0
 */
@EnableWebMvc
@EnableAspectJAutoProxy(proxyTargetClass=true)
//@ImportResource(value = { "classpath:config/shiro/spring-shiro.xml"})
@ComponentScan(basePackages = {"org.xfs.scm.**.web","org.xfs.**.api","com.qht.**.api","org.xfs.scm.**.advice"})
public class SpringWebConfig extends WebMvcConfigurerAdapter {

    private final static Logger logger= LoggerFactory.getLogger(SpringWebConfig.class);
    @Bean
    public ThreadPoolTaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor taskExecutor=new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(6);
        taskExecutor.setKeepAliveSeconds(300);
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.setQueueCapacity(25);
        return taskExecutor;
    }

    /**
     * {@inheritDoc}
     * <p>This implementation is empty.
     */
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(30);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("ronnie-task-");
        executor.initialize();
        configurer.setDefaultTimeout(30000);
        configurer.setTaskExecutor(executor);
    }
    //静态资源的处理
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }

    //开启shiro权限注解支持
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        if(logger.isDebugEnabled()){
            logger.debug("----------init SpringWebConfig -----------------"+securityManager);
        }
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor= new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
    /*
    * Configure ContentNegotiatingViewResolver 视图配置
    */
    @Bean
    public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setContentNegotiationManager(manager);

        List<ViewResolver> resolvers = new ArrayList<ViewResolver>();
        resolvers.add(jspViewResolver());
        // resolvers.add(freeMarkerViewResolver());
        resolver.setViewResolvers(resolvers);
        MappingJackson2JsonView defaultView = new MappingJackson2JsonView();
        // JsonEncoding encoding=new ();
        defaultView.setEncoding(JsonEncoding.UTF8);

        defaultView.setExtractValueFromSingleKeyModel(true);
        defaultView.setPrettyPrint(true);
        resolver.setDefaultViews(Lists.<View>newArrayList(defaultView));
        return resolver;
    }

    /**
     * 描述 : <文件上传处理器>. <br>
     *<p>
     <使用方法说明>
     </p>
     * @return
     */
    @Bean(name="multipartResolver")
    public CommonsMultipartResolver commonsMultipartResolver(){
        if(logger.isDebugEnabled()){
            logger.info("CommonsMultipartResolver");
        }
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("UTF-8");
        return multipartResolver;
    }
    private InternalResourceViewResolver jspViewResolver() {
        InternalResourceViewResolver viewResolver= new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    /**
     * restful json 格式化覆写
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

        Optional<HttpMessageConverter<?>> converterFound;
        converterFound = converters.stream().filter(c -> c instanceof AbstractJackson2HttpMessageConverter).findFirst();

        if (converterFound.isPresent()) {
            final AbstractJackson2HttpMessageConverter converter;
            converter = (AbstractJackson2HttpMessageConverter) converterFound.get();
            converter.getObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
            converter.getObjectMapper().enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
           // converter.getObjectMapper().setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));//日期格式
        }
    }
//        @Override
//        public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//            converters.add(mappingJackson2HttpMessageConverter());
//        }
//
//    @Bean
//    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//        converter.setObjectMapper(new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false));
//        return converter;
//    }

    @Bean
    public MyExceptionHandler myExceptionHandler(){
        if(logger.isDebugEnabled()){
            logger.debug("-----------------------init MyExceptionHandler--------------------------------------");
        }
        return new MyExceptionHandler();
    }


}

