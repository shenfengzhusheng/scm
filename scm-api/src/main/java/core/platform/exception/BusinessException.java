package core.platform.exception;

import java.io.StringWriter;

public class BusinessException extends RuntimeException {

    /**
	 * 
	 */
    private static final long serialVersionUID = 6680346093252653285L;

    private String message;
    private String code;

    /**
     * 构造函数
     * 
     * @param result
     */
    public BusinessException(String message) {
        this.message = message;
    }



    /**
     * 重写异常堆栈方法，去除堆栈信息，优化异常的内存占用
     */
    @Override
    public StackTraceElement[] getStackTrace() {
        return new StackTraceElement[0];
    }

    /**
     * 项目名称：webservice <br>
     * 类名称：ExceptionUtil.java<br>
     * 方法描述： 返回错误信息字符串<br>
     * Copyright: Copyright (c) 西风团 <br>
     * Result:String<br>
     * Param:@param e 错误信息字符串 Param:@return<br>
     * 创建人：神风逐胜<br>
     * 创建时间：2014年11月14日 上午10:49:49 <br>
     * 修改说明：<br>
     * 
     * @version 1.0
     */
    public static String getExceptionMessage(Exception ex) {
        StringWriter sw = new StringWriter();
        ex.printStackTrace();
        return sw.toString();
    }



    public String getMessage() {
        return message;
    }



    public void setMessage(String message) {
        this.message = message;
    }



    public String getCode() {
        return code;
    }



    public void setCode(String code) {
        this.code = code;
    }
}
