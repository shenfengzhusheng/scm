package org.xfs.scm.platform.base.enums;

import java.io.Serializable;

public interface EnumI<T> extends Serializable{
    /**
     * 获取枚举编码
     * @return
     */
    T getCode();
    /**
     * 获取枚举说明
     * @return
     */
    String getName();
}
