package org.xfs.scm.platform.config.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.text.MessageFormat;

/**
 * Created by 神风逐胜 on 2018/1/17 0017.23:16
 * version:1.0
 */
public class MyRequestAndSessionAttributeListener implements HttpSessionAttributeListener, ServletRequestAttributeListener {
    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        String str= MessageFormat.format("HttpSession域对象中添加了属性:{0}，属性值是:{1}",
                httpSessionBindingEvent.getName(),
                httpSessionBindingEvent.getValue());
        System.out.println(str);
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        String str= MessageFormat.format("HttpSession域对象中删除了属性:{0}，属性值是:{1}",
                httpSessionBindingEvent.getName(),
                httpSessionBindingEvent.getValue());
        System.out.println(str);
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
        String str= MessageFormat.format("HttpSession域对象中替换了属性:{0}，属性值是:{1}",
                httpSessionBindingEvent.getName(),
                httpSessionBindingEvent.getValue());
        System.out.println(str);
    }

    @Override
    public void attributeAdded(ServletRequestAttributeEvent servletRequestAttributeEvent) {
        String str= MessageFormat.format("ServletRequest域对象中添加了属性:{0}，属性值是:{1}",
                servletRequestAttributeEvent.getName(),
                servletRequestAttributeEvent.getValue());
        System.out.println(str);
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent servletRequestAttributeEvent) {
        String str= MessageFormat.format("ServletRequest域对象中删除了属性:{0}，属性值是:{1}",
                servletRequestAttributeEvent.getName(),
                servletRequestAttributeEvent.getValue());
        System.out.println(str);
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent servletRequestAttributeEvent) {
        String str= MessageFormat.format("ServletRequest域对象中替换了属性:{0}，属性值是:{1}",
                servletRequestAttributeEvent.getName(),
                servletRequestAttributeEvent.getValue());
        System.out.println(str);
    }
}
