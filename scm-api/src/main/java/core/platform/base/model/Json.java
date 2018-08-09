package core.platform.base.model;

import java.io.Serializable;

/**
 * Created by 神风逐胜 on 2017/9/24 0024.8:47
 * version:1.0
 */
public class Json<T>implements Serializable {
    private String code="400";
    private String message;
    private T data;

    public Json() {
    }

    public Json(String message) {
        this.message=message;
    }

    public Json(String code,String message,T data){
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
