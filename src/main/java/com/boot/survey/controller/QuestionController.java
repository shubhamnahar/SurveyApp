package com.boot.survey.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.boot.survey.requestBeans.QuestionRequest;
import com.boot.survey.service.QuestionService;


@RestController
//@RequestMapping("question")
public class QuestionController {

//	private static final Logger logging =LoggerFactory.getLogger(QuestionController.class);
	 @Autowired
	    private QuestionService questionService;

	    @PostMapping("addQuestion")
	    public ResponseEntity<String> createQuestion(@RequestBody QuestionRequest request) {
	    	
	        String savedQuestion = questionService.saveQuestion(request);

	        
	        return new ResponseEntity<>(savedQuestion, HttpStatus.CREATED);
	    }
	    
	    
	    @GetMapping("viewQuestion/{question_id}")
	    public ResponseEntity<QuestionRequest> viewQuestion(@PathVariable Integer question_id){
	    	QuestionRequest question = questionService.viewQuestion(question_id);
	    	return new ResponseEntity<>(question,HttpStatus.OK);
	    }
	    
	    @DeleteMapping("deleteQuestion/{question_id}")
	    public ResponseEntity<String> deleteQuestion(@PathVariable Integer question_id){
	    	questionService.deleteQuestion(question_id);
	    	return new ResponseEntity<>("Question deleted successfully.",HttpStatus.OK);
	    }
	
}
