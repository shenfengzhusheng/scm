package org.xfs.scm.platform.config.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import java.text.MessageFormat;

/**
 * Created by 神风逐胜 on 2018/1/17 0017.22:54
 * version:1.0
 */
public class MyServletContextAttributeListener implements ServletContextAttributeListener{
    @Override
    public void attributeAdded(ServletContextAttributeEvent servletContextAttributeEvent) {
        String str= MessageFormat.format("ServletContext域对象中添加了属性:{0}，属性值是:{1}",
                servletContextAttributeEvent.getName(),
                servletContextAttributeEvent.getValue());
        System.out.println(str);
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent) {
        String str= MessageFormat.format("ServletContext域对象中删除了属性:{0}，属性值是:{1}",
                servletContextAttributeEvent.getName(),
                servletContextAttributeEvent.getValue());
        System.out.println(str);
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent servletContextAttributeEvent) {
        String str= MessageFormat.format("ServletContext域对象中替换了属性:{0}，属性值是:{1}",
                servletContextAttributeEvent.getName(),
                servletContextAttributeEvent.getValue());
        System.out.println(str);
    }


}
