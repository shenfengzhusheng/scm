package org.xfs.scm.platform.config.spring;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
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
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"org.xfs.scm.**.web","org.xfs.scm.**.advice"})
public class SpringWebConfig extends WebMvcConfigurerAdapter {

    private final static Logger logger= LoggerFactory.getLogger(SpringWebConfig.class);

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
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
        logger.info("-----------------------init MyExceptionHandler--------------------------------------");
        return new MyExceptionHandler();
    }


}

