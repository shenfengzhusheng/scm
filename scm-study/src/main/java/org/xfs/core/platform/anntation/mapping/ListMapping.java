package org.xfs.core.platform.anntation.mapping;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ListMapping
 * 
 * @author Jeken.Liu
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ListMapping {

    /**
     * 目标对象
     */
    Class<?> destinationClass() default Class.class;

    /**
     * 目标属性名称
     */
    String key() default "";
}
