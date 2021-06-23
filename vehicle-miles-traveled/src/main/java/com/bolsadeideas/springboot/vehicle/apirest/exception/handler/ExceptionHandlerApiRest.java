package com.bolsadeideas.springboot.vehicle.apirest.exception.handler;

import java.net.ConnectException;
import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bolsadeideas.springboot.vehicle.apirest.exception.CountyIdExistException;
import com.bolsadeideas.springboot.vehicle.apirest.exception.CountyNotFoundException;
import com.bolsadeideas.springboot.vehicle.apirest.exception.StateIdExistException;
import com.bolsadeideas.springboot.vehicle.apirest.exception.StateNotFoundException;
import com.bolsadeideas.springboot.vehicle.apirest.exception.VMTCountyIdExistException;
import com.bolsadeideas.springboot.vehicle.apirest.exception.VMTCountyNotFoundException;




@RestControllerAdvice
public class ExceptionHandlerApiRest {
	
	@ExceptionHandler(CountyNotFoundException.class)
	public ResponseEntity<StandarizedExceptionResponse> countyNotFoundHandler(CountyNotFoundException ex) {
		StandarizedExceptionResponse response=new StandarizedExceptionResponse("County no existe o o no se ha especificado",ex.getMessage(), HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);	
	}
	
	@ExceptionHandler(CountyIdExistException.class)
	public ResponseEntity<StandarizedExceptionResponse> countyIdExistException(CountyIdExistException ex) {
		StandarizedExceptionResponse response=new StandarizedExceptionResponse("County se encuentra registrado",ex.getMessage(), HttpStatus.BAD_REQUEST.value());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);	
	}
	
	@ExceptionHandler(StateNotFoundException.class)
	public ResponseEntity<StandarizedExceptionResponse> stateNotFoundHandler(StateNotFoundException ex) {
		StandarizedExceptionResponse response=new StandarizedExceptionResponse("State no existe o no se ha especificado",ex.getMessage(), HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);	
	}
	
	@ExceptionHandler(StateIdExistException.class)
	public ResponseEntity<StandarizedExceptionResponse> stateIdExistException(StateIdExistException ex) {
		StandarizedExceptionResponse response=new StandarizedExceptionResponse("State se encuentra registrado",ex.getMessage(), HttpStatus.BAD_REQUEST.value());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);	
	}
	
	@ExceptionHandler(VMTCountyNotFoundException.class)
	public ResponseEntity<StandarizedExceptionResponse> vmtCountyNotFoundHandler(VMTCountyNotFoundException ex) {
		StandarizedExceptionResponse response=new StandarizedExceptionResponse("Registro no existe",ex.getMessage(), HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);	
	}
	
	@ExceptionHandler(VMTCountyIdExistException.class)
	public ResponseEntity<StandarizedExceptionResponse> vmtCountyIdExistException(VMTCountyIdExistException ex) {
		StandarizedExceptionResponse response=new StandarizedExceptionResponse("Registro se encuentra en la base de datos",ex.getMessage(), HttpStatus.BAD_REQUEST.value());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);	
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<StandarizedExceptionResponse> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
		StandarizedExceptionResponse response=new StandarizedExceptionResponse("Request method not supported",ex.getMessage(), HttpStatus.BAD_REQUEST.value());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);	
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<StandarizedExceptionResponse> httpMessageNotReadableException(HttpMessageNotReadableException ex) {
		StandarizedExceptionResponse response=new StandarizedExceptionResponse("Json no valido",ex.getMostSpecificCause().getMessage(), HttpStatus.BAD_REQUEST.value());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	
	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<StandarizedExceptionResponse> numberFormatException(NumberFormatException ex) {
		StandarizedExceptionResponse response=new StandarizedExceptionResponse("Numero Ingresado no valido",ex.getMessage(), HttpStatus.BAD_REQUEST.value());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);	
	}
	
	@ExceptionHandler(ConnectException.class)
	public ResponseEntity<StandarizedExceptionResponse> connectException(ConnectException ex) {
		StandarizedExceptionResponse response=new StandarizedExceptionResponse("Error en la conección con base de datos",ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);	
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandarizedExceptionResponse> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
		
		Map<String, String> errors=new HashMap<>();
		
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String message=error.getDefaultMessage();
			String field=((FieldError)error).getField();
			errors.put("message", message);
			errors.put("field", field);
		});
		
		StandarizedExceptionResponse response=new StandarizedExceptionResponse("Json no valido"," Campo "+errors.get("field")+" debe estar definido y "+errors.get("message"), HttpStatus.BAD_REQUEST.value());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);	
	}
	

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<StandarizedExceptionResponse> httpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
		StandarizedExceptionResponse response=new StandarizedExceptionResponse(ex.getCause().getMessage(),ex.getLocalizedMessage(), HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
		return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(response);	
	}

}
