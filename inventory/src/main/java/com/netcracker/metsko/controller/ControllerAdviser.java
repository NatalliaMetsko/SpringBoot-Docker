package com.netcracker.metsko.controller;

import com.netcracker.metsko.entity.ErrorMessage;
import com.netcracker.metsko.exceptions.NotCreatedException;
import com.netcracker.metsko.exceptions.NotDeletedException;
import com.netcracker.metsko.exceptions.NotFoundException;
import com.netcracker.metsko.exceptions.NotUpdatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ControllerAdviser {


    @ExceptionHandler(NotCreatedException.class)
    public ResponseEntity<ErrorMessage> handlerNotCreatedException()
    {
        ErrorMessage errorMessage = new ErrorMessage(500, "Not created");
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);

    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> handlerNotFoundException()
    {
        ErrorMessage errorMessage= new ErrorMessage(404,"Not found");
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(NotDeletedException.class)
    public ResponseEntity<ErrorMessage> handlerNotDeletedException()
    {
        ErrorMessage errorMessage = new ErrorMessage( 500, "Not deleted");
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotUpdatedException.class)
    public ResponseEntity<ErrorMessage> handlerNotUpdatedException()
    {
        ErrorMessage errorMessage = new ErrorMessage(500, "Not updated");
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
