package se.ifmo.ru.web.controller.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import se.ifmo.ru.exception.NotFoundException;
import se.ifmo.ru.util.ResponseUtils;
import se.ifmo.ru.web.model.Error;

@ControllerAdvice
@Slf4j
public class MainControllerAdvice {
    private ResponseUtils responseUtils;

    public MainControllerAdvice(ResponseUtils responseUtils) {
        this.responseUtils = responseUtils;
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Error> handleNotFoundException(NotFoundException e) {
        log.error(e.getMessage(), e);
        return responseUtils.buildResponseWithMessage(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Error> handleThrowable(Throwable e) {
        log.error(e.getMessage(), e);
        return responseUtils.buildResponseWithMessage(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
}
