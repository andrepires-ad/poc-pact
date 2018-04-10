package com.appdirect.pact.provider;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// TODO hould only be available when running an instance for the Pact Contract Test
@RestController
public class PactStateChangeController {

	@RequestMapping(value = "/pactcontracts/states",
			produces = { "application/json" },
			consumes = { "application/json" },
			method = RequestMethod.POST)
	public ResponseEntity<String> stateChange(
			@RequestBody final Map<String, Object> value) {
		System.out.println("state: " + value.get("state"));
		return ResponseEntity.status(INTERNAL_SERVER_ERROR).body("{\"message\": \"status changed: " + value.get("state") + "\" }");
	}
}
