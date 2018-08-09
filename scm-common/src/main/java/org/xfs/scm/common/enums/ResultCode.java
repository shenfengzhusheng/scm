package org.xfs.scm.common.enums;


/**
 * 响应码枚举，参考HTTP状态码的语义
 * Created by 神风逐胜 on 2017/9/24 0024.9:25
 * version:1.0
 */
public enum ResultCode {

    SUCCESS("200","成功"),//成功
    FAIL("400","失败"),//失败
    UNAUTHORIZED("401","未认证"),//未认证（签名错误）
    NOT_FOUND("404","接口不存在"),//接口不存在
    INTERNAL_SERVER_ERROR("500","服务器内部错误");//服务器内部错误
    /**编码**/
    private String code;
    /**名称**/
    private String name;

    ResultCode(String code,String name){
        this.code=code;
        this.name=name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return this.code + "=" + this.name;
    }

    /**
     * 获取对应的中文名称.
     * @param code
     * @return
     */
    public static String getName(String code){
        for(ResultCode result:ResultCode.values()){
            if(result.getCode().equals(code)){
                return result.getName();
            }
        }
        return null;
    }

    public static void main(String []args){
        System.out.println(ResultCode.getName("401"));
    }
}
