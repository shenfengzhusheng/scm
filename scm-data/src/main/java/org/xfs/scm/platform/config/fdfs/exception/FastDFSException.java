package org.xfs.scm.platform.config.fdfs.exception;

import org.xfs.scm.common.exception.BusinessException;

/**
 * fastDFS自定义异常处理
 */
public class FastDFSException extends BusinessException {

    public FastDFSException(){
        super("文件上传异常！","5000");
    }

    public FastDFSException(String error){
        super(error,"5001");
    }
}
