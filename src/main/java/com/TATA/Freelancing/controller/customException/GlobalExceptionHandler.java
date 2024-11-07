package com.TATA.Freelancing.controller.customException;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exObj)
	{
		Map<String,Object> response=new HashMap<>();
		response.put("msg",exObj.getMessage() );
		response.put("Status","failure");
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}

}
