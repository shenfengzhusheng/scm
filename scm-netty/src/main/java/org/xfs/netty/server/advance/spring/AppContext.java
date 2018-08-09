package org.xfs.netty.server.advance.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.xfs.mq.spring.config.ActiveMqConfig;

@ComponentScan(basePackages = {"org.xfs.netty"})
public class AppContext implements ApplicationContextAware {
    public static final String TCP_SERVER = "tcpServer";

//    private static ApplicationContext  applicationContext= new ClassPathXmlApplicationContext("spring.xml");//读取bean.xml中的内容;
    public static ApplicationContext  applicationContext=new AnnotationConfigApplicationContext(ActiveMqConfig.class,SpringConfig.class);
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException{
         AppContext.applicationContext=applicationContext;
    }


    // 根据beanName获取bean
    public static Object getBean(String beanName){
        if (null == beanName){
            return null;
        }
        return applicationContext.getBean(beanName);
    }

    public static <T> T  getBean(Class<T>clazz){
        return applicationContext.getBean(clazz);
    }




}
