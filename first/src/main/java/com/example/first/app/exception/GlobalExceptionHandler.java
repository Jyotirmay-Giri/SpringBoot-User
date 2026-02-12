package com.example.first.app.exception;

import com.example.first.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
@RestControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.toString());

    @ExceptionHandler({UserNotFoundException.class,IllegalArgumentException.class, NullPointerException.class})
    public ResponseEntity<Map<String, Object>> handleIlligalArgumentException(
            Exception exception){
        logger.error("Error when finding user : ", exception);
        Map<String,Object> errorResponse = new HashMap<>();
        errorResponse.put("message",exception.getMessage());
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler( HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Map<String, Object>> handleMethodNotSupported(
            Exception exception){
        Map<String,Object> errorResponse = new HashMap<>();
        errorResponse.put("message",exception.getMessage());
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", HttpStatus.METHOD_NOT_ALLOWED);
        errorResponse.put("error", "Method is not allowed");
        return new ResponseEntity<>(errorResponse,HttpStatus.METHOD_NOT_ALLOWED);

    }
}
