package com.shoping.exceptions.handlers;

import com.shoping.domain.dto.requests.ErrorResponse;
import com.shoping.exceptions.ShopingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ShopingExceptionHandler {
    @ExceptionHandler(ShopingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleTaskManagementException(ShopingException e) {
        return ErrorResponse.fromString(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ErrorResponse.fromBindingResult(e.getBindingResult());
    }
}
