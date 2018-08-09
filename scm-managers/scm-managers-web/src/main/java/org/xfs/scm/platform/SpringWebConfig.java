package org.xfs.scm.platform;


import com.fasterxml.jackson.core.JsonEncoding;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.xfs.scm.platform.exception.MyExceptionHandler;

import java.util.ArrayList;
import java.util.List;


@EnableWebMvc
@Configuration
@EnableAspectJAutoProxy
@EnableSpringConfigured
// 引入xml
// @ImportResource(value = { "classpath:META-INF/aop.xml", ..})
// 那里想用aop那里配这个
@ComponentScan(basePackages = {"org.xfs.core.**.web", "org.xfs.core.**.advice"})
public class SpringWebConfig extends WebMvcConfigurerAdapter {

    private final static Logger logger = LoggerFactory.getLogger(SpringWebConfig.class);


    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    // @Override
    // public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
    //
    // configurer.ignoreAcceptHeader(true).favorPathExtension(true).useJaf(false).favorParameter(true).parameterName("mediaType")
    // .mediaType("do", MediaType.TEXT_HTML).mediaType("json", MediaType.APPLICATION_JSON).defaultContentType(MediaType.TEXT_HTML);
    // }

    //
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


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    // @Bean
    // public MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter() {
    // MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
    // List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
    // supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
    //
    // mappingJacksonHttpMessageConverter.setSupportedMediaTypes(supportedMediaTypes);
    //
    // return mappingJacksonHttpMessageConverter;
    // }

    @Bean
    public InternalResourceViewResolver jspViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    // @Bean
    // public SessionLocaleResolver localeResolver() {
    // // Enable the SessionLocaleResolver
    // // Even if you don't localize your webapp you should still specify this
    // // so that things like numbers, dates, and currencies are formatted properly
    // SessionLocaleResolver localeResolver = new SessionLocaleResolver();
    // localeResolver.setDefaultLocale(Locale.CHINA);
    //
    // return localeResolver;
    // }
    //
    // @Bean(name = "multipartResolver")
    // public CommonsMultipartResolver getMultipartResolver() {
    // return new CommonsMultipartResolver();
    // }

    // @Bean(name = "messageSource")
    // public ReloadableResourceBundleMessageSource getMessageSource() {
    // ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
    // resource.setBasename("classpath:i18n");
    // resource.setDefaultEncoding("UTF-8");
    // return resource;
    // }

    @Bean(name = "myExceptionHandler")
    public MyExceptionHandler myExceptionHandler() {
        logger.info("-----------------------init MyExceptionHandler--------------------------------------");
        return new MyExceptionHandler();
    }

}
