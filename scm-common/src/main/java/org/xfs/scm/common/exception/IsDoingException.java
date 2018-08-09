package org.xfs.scm.common.exception;

/**
 * 正在处理异常
 * Created by 神风逐胜 on 2017/9/24 0024.13:09
 * version:1.0
 */
public class IsDoingException extends BusinessException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3755142089455920962L;

	public IsDoingException(){
        super("正在处理中");
    }
}
