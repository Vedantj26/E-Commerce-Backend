package net.javaguide.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import net.javaguide.springboot.model.ResponseHandler;

@ControllerAdvice
public class UserExceptionController {
	@ExceptionHandler(value = RecordNotFoundException.class)
	public ResponseEntity<Object> exception(RecordNotFoundException exception) {
		return ResponseHandler.generateResponse(exception.getMessage(), HttpStatus.NOT_FOUND, null, true);
	}

	@ExceptionHandler(value = AlreadyExistsException.class)
	public ResponseEntity<Object> exception(AlreadyExistsException exception) {
		return ResponseHandler.generateResponse(exception.getMessage(), HttpStatus.NOT_FOUND, null, true);
	}

}
