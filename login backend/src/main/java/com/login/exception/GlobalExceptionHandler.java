package com.login.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;



@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleRNFException
	(ResourceNotFoundException rNFException, WebRequest request )
	{
		IdError errorId =new IdError(new Date(),rNFException.getMessage(),request.getDescription(false));
		return new ResponseEntity(errorId,HttpStatus.NOT_FOUND);

	}

}