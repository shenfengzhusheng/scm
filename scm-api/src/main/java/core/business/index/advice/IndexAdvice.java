package core.business.index.advice;

import core.platform.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ControllerAdvice(annotations = Controller.class)
public class IndexAdvice {
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public ExceptionResponse bindResult(BindException result) {
        ExceptionResponse response = new ExceptionResponse(400, "");
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
}
