package net.javaguide.springboot.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
	public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", message);
		map.put("status", status.value());
		map.put("data", responseObj);

		return new ResponseEntity<Object>(map, status);
	}

	public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj,
			boolean err) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", message);
		map.put("status", status.value());
		map.put("data", responseObj);
		map.put("error", true);

		return new ResponseEntity<Object>(map, status);
	}

}
