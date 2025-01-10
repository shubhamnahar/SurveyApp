package com.boot.survey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.boot.survey.model.QuestionTable;
import com.boot.survey.requestBeans.QuestionRequest;
import com.boot.survey.service.QuestionService;


@RestController
//@RequestMapping("question")
public class QuestionController {

	
	 @Autowired
	    private QuestionService questionService;

	    @PostMapping("addQuestion")
	    public ResponseEntity<String> createQuestion(@RequestBody QuestionRequest request) {
	    	
	        String savedQuestion = questionService.saveQuestion(request);
	        System.out.println("saved Response  is "+savedQuestion);
	        return new ResponseEntity<>(savedQuestion, HttpStatus.CREATED);
	    }
	
}
