package com.yugioh.yugioh.errors;


import com.yugioh.yugioh.exceptions.YugiohException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

	@ExceptionHandler({YugiohException.class})
	public ResponseEntity<ErrorResponse> handleConstraintViolation(Exception ex) {
		ErrorResponse error = new ErrorResponse(ex.getLocalizedMessage(), "YugiohException", ex.getCause());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
