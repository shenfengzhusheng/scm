package org.xfs.scm.common.base.model;

import org.xfs.scm.common.enums.ResultCode;

import java.io.Serializable;

/**
 * Created by 神风逐胜 on 2017/9/24 0024.9:16
 * version:1.0
 */
public class AjaxResult<T> implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7987556089180089472L;
	/**返回代码*/
    private String code= ResultCode.FAIL.getCode();
    /**返回信息*/
    private String message;
    /**返回值*/
    private T data;

    public AjaxResult() {
    }

    public AjaxResult(String message) {
        this.message=message;
    }

    public AjaxResult(String code, String message, T data){
        this.code=code;
        this.message=message;
        this.data=data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
