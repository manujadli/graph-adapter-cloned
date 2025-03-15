package com.example.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/graph")
public class GraphController {

	@GetMapping("/greetings")
	public ResponseEntity<?> greetings(){
		System.out.println("GraphController.greetings()");		
		return new ResponseEntity<String>("Hello World!", HttpStatus.OK);
	}
	
}
