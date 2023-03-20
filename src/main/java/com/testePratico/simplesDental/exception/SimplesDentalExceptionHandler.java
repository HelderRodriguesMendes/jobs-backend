package com.testePratico.simplesDental.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class SimplesDentalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String CONSTANT_VALIDATION_NOT_BLANK = "NotBlank";
    private static final String CONSTANT_VALIDATION_NOT_NULL = "NotNull";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        MenssagensErro menssagensErro = getListErrors(ex.getBindingResult());
        return super.handleExceptionInternal(ex, menssagensErro, headers, HttpStatus.BAD_REQUEST, request);
    }

    private MenssagensErro getListErrors(BindingResult bindingResult){
        MenssagensErro menssagensErros = new MenssagensErro();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            String msgErro = MsgErrorUser(fieldError);
            menssagensErros.setMsgErro(msgErro);
        });
        return menssagensErros;
    }

    private String MsgErrorUser(FieldError fieldError){
        if(fieldError.getCode().equals(CONSTANT_VALIDATION_NOT_BLANK)){
            return fieldError.getDefaultMessage().concat(" é obrigatório");
        }else if(fieldError.getCode().equals(CONSTANT_VALIDATION_NOT_NULL)){
            return fieldError.getDefaultMessage().concat(" é obrigatório");
        }
        return fieldError.getField();
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> objectNotFound(NotFoundException e, HttpServletRequest request){
        String msgError = e.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msgError);
    }

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<Object> handleRegraNegocioException(RegraNegocioException ex, WebRequest request){
        String msgError = ex.getMessage();
        return handleExceptionInternal(ex, msgError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}