package com.solera.springboot.vaccinationmanagement.CovidManagementSystem.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.solera.springboot.vaccinationmanagement.CovidManagementSystem.entity.CitizenErrorResponse;

@ControllerAdvice
public class CitizenExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<CitizenErrorResponse> handleException(CitizenNotFoundException cnfe)
	{
		CitizenErrorResponse error=new CitizenErrorResponse(HttpStatus.NOT_FOUND.value(),
				cnfe.getMessage(),System.currentTimeMillis());
		
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<CitizenErrorResponse> handleException(CitizenBadRequestException cbre)
	{
		CitizenErrorResponse error=new CitizenErrorResponse(HttpStatus.BAD_REQUEST.value(),
				cbre.getMessage(),System.currentTimeMillis());
		
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler
	public ResponseEntity<CitizenErrorResponse> handleException(InvalidDateException ide)
	{
		CitizenErrorResponse error=new CitizenErrorResponse(HttpStatus.BAD_REQUEST.value(),
				ide.getMessage(),System.currentTimeMillis());
		
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler
	public ResponseEntity<CitizenErrorResponse> handleException(Exception e)
	{
		CitizenErrorResponse error=new CitizenErrorResponse(HttpStatus.BAD_REQUEST.value(),
				e.getMessage(),System.currentTimeMillis());
		
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
}
