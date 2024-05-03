package com.library.management.ExceptionHandler;

import com.library.management.Exceptions.IdNotFoundException;
import com.library.management.Utils.MyResponseGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.FileHandler;

@ControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(value= IdNotFoundException.class)
    @ResponseStatus(value=HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> exceptionHandler()
    {
        return MyResponseGenerator.generateResponse(HttpStatus.BAD_REQUEST,false,"Id is not found",null);
    }

    @ExceptionHandler(value= NullPointerException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> nullPointerHandler()
    {
        return MyResponseGenerator.generateResponse(HttpStatus.BAD_REQUEST,false,"Null Pointer Exception",null);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> exceptionHandle()
    {
        return MyResponseGenerator.generateResponse(HttpStatus.BAD_REQUEST,false,"There is Exception",null);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleArgumentExp(MethodArgumentNotValidException methodArgumentNotValidException)
    {
        Map<String,String> errorsMap= new HashMap<>();
        methodArgumentNotValidException.getBindingResult()
                .getAllErrors()
                .forEach((error)->
        {
            String fieldName=((FieldError)error).getField();
            String errorMessage=((FieldError)error).getDefaultMessage();
            errorsMap.put(fieldName,errorMessage);
        });

        return MyResponseGenerator.generateResponse(HttpStatus.BAD_REQUEST,false,"Server side error",errorsMap);
    }

}
