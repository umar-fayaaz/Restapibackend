package com.exotorad.backend.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class MyExceptionHandler {

	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<Object> handle() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error", "Url is Incorrect , check and try");

		return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Object> handle1() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error", "Enter Proper Value in the Path");

		return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
	}

}