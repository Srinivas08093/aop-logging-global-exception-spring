package com.example.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.application.utill.ApplicationUtill;
import com.example.application.utill.ResponseData;

/**
 * 
 * @author Srinivas Nangana
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseData> invalidArgumentFieldExceptionHandler(MethodArgumentNotValidException ex) {

		ResponseData ResponseData = new ResponseData(ApplicationUtill.RES_CODE_FAILURE,
				ApplicationUtill.SOME_ERROR_MESSAGE);
		ResponseData.addValidations(ApplicationUtill.SOME_ERROR_MESSAGE, ApplicationUtill.SOME_ERROR_MESSAGE);

		return new ResponseEntity<>(ResponseData, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseData> exceptionHandler(Exception ex) {

		ResponseData ResponseData = new ResponseData(ApplicationUtill.RES_CODE_FAILURE,
				ApplicationUtill.SOME_ERROR_MESSAGE);
		ResponseData.addValidations(ApplicationUtill.SOME_ERROR_MESSAGE, ApplicationUtill.RES_CODE_FAILURE);
		
		return new ResponseEntity<>(ResponseData, HttpStatus.INTERNAL_SERVER_ERROR);  
		

	}

}