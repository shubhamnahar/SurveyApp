package com.boot.survey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.boot.survey.model.UserTable;
import com.boot.survey.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	UserService userservice;
	
	@PostMapping(value = "createUser",  consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> CreateUser(@RequestBody UserTable usertable) {
		String s = userservice.CreateUser(usertable);
		return new ResponseEntity<>(s,HttpStatus.CREATED);

	}
}
