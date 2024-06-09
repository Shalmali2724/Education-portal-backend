package com.eduPortal.education_portal.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

//ExceptionHandler for Validations
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleUserNotFoundException(Exception e) {
	ResponseEntity<String> responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		
		return responseEntity;
	}
	
	@ExceptionHandler(UserAlreadyRegistredException.class)
	public ResponseEntity<String> handleUserAlreadyRegistredException(Exception e) {
	ResponseEntity<String> responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		
		return responseEntity;
	}
	

	@ExceptionHandler(AdminNotFoundException.class)
	public ResponseEntity<String> handleAdminNotFoundException(Exception e) {
	ResponseEntity<String> responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		
		return responseEntity;
	}
	

	@ExceptionHandler(CourseNotFoundException.class)
	public ResponseEntity<String> handleCourseNotFoundException(Exception e) {
	ResponseEntity<String> responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		
		return responseEntity;
	}
	
	@ExceptionHandler(CartGetException.class)
	public ResponseEntity<String> handleCartGetException(Exception e) {
	ResponseEntity<String> responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		
		return responseEntity;
	}
	
	

}
