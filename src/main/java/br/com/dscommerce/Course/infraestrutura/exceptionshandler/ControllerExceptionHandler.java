package br.com.dscommerce.Course.infraestrutura.exceptionshandler;

import br.com.dscommerce.Course.infraestrutura.exceptions.ResourceNotFoundException;
import br.com.dscommerce.Course.infraestrutura.exceptionsdto.ArgumentErrorField;
import br.com.dscommerce.Course.infraestrutura.exceptionsdto.CustomError;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity resourceNotFound(ResourceNotFoundException resourceNotFoundException, HttpServletRequest httpServletRequest){
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError customError = new CustomError(Instant.now(), status.value(), resourceNotFoundException.getMessage(),
                httpServletRequest.getRequestURI());
        return ResponseEntity.status(status).body(customError);

    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity entityNotFound(EntityNotFoundException entityNotFoundException, HttpServletRequest httpServletRequest){
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError customError = new CustomError(Instant.now(), status.value(), " ",
                httpServletRequest.getRequestURI());
        return ResponseEntity.status(status).body(customError);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValid(MethodArgumentNotValidException notValid, HttpServletRequest httpServletRequest){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ArgumentErrorField argumentErrorField = new ArgumentErrorField(Instant.now(), status.value(),
                httpServletRequest.getRequestURI(), notValid.getFieldError().getField(), notValid.getFieldError().getDefaultMessage());

        return ResponseEntity.badRequest().body(argumentErrorField);

    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity messageNotReable(HttpMessageNotReadableException notValid,HttpServletRequest httpServletRequest){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ArgumentErrorField argumentErrorField = new ArgumentErrorField(Instant.now(), status.value(),
                httpServletRequest.getRequestURI(),  " ", notValid.getMessage());

        return ResponseEntity.badRequest().body(argumentErrorField);

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity integrityViolation(DataIntegrityViolationException notValid, HttpServletRequest httpServletRequest){
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ArgumentErrorField argumentErrorField = new ArgumentErrorField(Instant.now(), status.value(), httpServletRequest.getRequestURI(),
                "id", notValid.getClass().getSimpleName());
        return ResponseEntity.badRequest().body(argumentErrorField);
    }

}
