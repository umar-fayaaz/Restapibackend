package com.exotorad.backend.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

/**
 * Global exception handler for handling application-specific exceptions and
 * returning custom error responses.
 */

@RestControllerAdvice
public class MyExceptionHandler {

	/**
	 * Handles {@link NoResourceFoundException} and returns a NOT_FOUND (404) error
	 * response.
	 *
	 * @return a response entity with error details and NOT_FOUND status
	 */

	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<Object> handle() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error", "Url is Incorrect , check and try");

		return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
	}

	/**
	 * Handles {@link MethodArgumentTypeMismatchException} and returns a BAD_REQUEST
	 * (400) error response.
	 *
	 * @return a response entity with error details and BAD_REQUEST status
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Object> handle1() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error", "Enter Proper Value in the Path");

		return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
	}

}