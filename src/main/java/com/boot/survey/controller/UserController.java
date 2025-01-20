package com.boot.survey.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
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
	
	@PatchMapping("{id}")
	public ResponseEntity<UserTable> UpdateUserInfo(@PathVariable Integer id, @RequestBody UserTable usertable){
		
		UserTable user= userservice.UpdateUserInfo(id,usertable);
		
		return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
	}
	
//	@GetMapping
//	public ResponseEntity<UserTable>getUserbyEmail(@RequestParam String email){
//		UserTable s = userservice.getUserbyEmail(email);
//		
//		return new ResponseEntity<>(s,HttpStatus.FOUND);
//	}
	
	@GetMapping("id")
	public ResponseEntity<UserTable>getUserbyId(@RequestParam Integer id){
		UserTable s = userservice.getUserbyId(id);
		
		return new ResponseEntity<>(s,HttpStatus.FOUND);
	}
	
	@DeleteMapping("id")
	public ResponseEntity<Map<String,Object>>deleteUserbyId(@RequestParam Integer id){
		 userservice.deleteUserbyId(id);
		 
		 Map<String, Object> response = new HashMap<>();
		    response.put("timestamp", LocalDateTime.now());
		    response.put("status", HttpStatus.OK.value());
		    response.put("message", "User Deleted Successfully.");
		    response.put("path", "users/id");

		    return ResponseEntity.status(HttpStatus.OK)
		            .body(response);
		
	}
}
