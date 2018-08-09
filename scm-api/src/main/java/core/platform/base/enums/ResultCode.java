package core.platform.base.enums;

/**
 *  响应码枚举，参考HTTP状态码的语义
 * Created by 神风逐胜 on 2017/9/24 0024.8:57
 * version:1.0
 */
public enum  ResultCode {
    SUCCESS("200"),//成功
    FAIL("400"),//失败
    UNAUTHORIZED("401"),//未认证（签名错误）
    NOT_FOUND("404"),//接口不存在
    INTERNAL_SERVER_ERROR("500");//服务器内部错误
    private String code;

    ResultCode(String code){
        this.code=code;
    }
}
