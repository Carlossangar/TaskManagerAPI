package com.example.TaskManagerAPI.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

	@GetMapping(path="/healthCheck")
	public boolean healthCheck() {
		return true;
	}
	
}
