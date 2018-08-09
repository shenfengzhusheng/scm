package org.xfs.scm.platform.config.advice;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.xfs.scm.common.base.model.JsonResponse;
import org.xfs.scm.common.exception.SqlException;

import java.io.StringWriter;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Jeken.Liu on 2017/10/10.
 */
@ControllerAdvice(annotations = Controller.class)
public class ExceptionAdvice {

    /**
     * 请求错误
     * @param result
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public JsonResponse<String> bindResult(BindException result) {
        JsonResponse<String> response = new JsonResponse<String>();
        String msg = "";
        if (result.hasErrors()) {
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
                msg += error.getDefaultMessage();

            }
            response.setMessage(msg);

            // System.out.println(new Gson().toJson());
        }
        return response;
    }

    /**
     * 数据校验异常
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public JsonResponse<String>  handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {
        JsonResponse<String>  response =new  JsonResponse<String>();
        String msg = "";
        BindingResult bindingResult = ex.getBindingResult();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            msg += fieldError.getDefaultMessage() + ", ";
        }

        System.out.println(msg);
        response.setMessage(msg);
        return response;
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
   public JsonResponse<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex){
        JsonResponse<String>  response =new  JsonResponse<String>();
        response.setCode(100001);
        response.setMessage("数据库异常！");
        return response;
   }

    @ExceptionHandler(SQLException.class)
    @ResponseBody
    public JsonResponse<String> handleSQLException(SQLException ex){
        JsonResponse<String>  response =new JsonResponse<String>();
        response.setMessage(ex.getMessage());
        return response;
    }
    @ExceptionHandler(SqlException.class)
    @ResponseBody
    public JsonResponse<String> handleSqlException(SqlException ex){
        JsonResponse<String>  response =new JsonResponse<String>();
        response.setMessage(ex.getMessage());
        return response;
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public JsonResponse<String> handleDuplicateKeyException(DuplicateKeyException e){
        return JsonResponse.fail("数据库中已存在该记录");
    }
    @SuppressWarnings("unused")
	private String  getExceptionMessage(Exception ex){
        StringWriter sw=new StringWriter();
        //PrintWriter pw=new PrintWriter(sw);
        ex.printStackTrace();
        return sw.toString();
    }
}
