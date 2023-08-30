package com.infy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.service.VotingApiService;

@RestController
public class MyRestController {

	@Autowired
	VotingApiService apiService;
	
	@GetMapping("/voting")
	public ResponseEntity<String> doVoting() {
		return apiService.doVoting();
	}
}
