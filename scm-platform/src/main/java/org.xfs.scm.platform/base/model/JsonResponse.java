package org.xfs.scm.platform.base.model;

import java.io.Serializable;

/**
 * Created by 神风逐胜 on 2017/9/24 0024.8:47
 * version:1.0
 */
@SuppressWarnings("serial")
public class JsonResponse<T>implements Serializable {
    private int code=400;
    private String message;
    private T data;

    public JsonResponse() {
    }
    public static JsonResponse<String> success(String msg){
        return new JsonResponse<String>(100,msg,"");
    }
    public static <T> JsonResponse<T> success(String msg,T data){
        return new JsonResponse<T>(100,msg,data);
    }

    public static <T> JsonResponse<T> fail(String msg){
        return new JsonResponse<T>(400,msg,null);
    }
    public JsonResponse(String message) {
        this.message=message;
    }

    public JsonResponse(int code,String message,T data){
        this.code=code;
        this.message=message;
        this.data=data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
