package com.boot.survey.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import com.boot.survey.model.SurveyDetailsTable;
import com.boot.survey.responseBeans.SurveyToQuestionBean;
import com.boot.survey.service.SurveyService;


@RestController
@RequestMapping("survey")
public class SurveyController {

	@Autowired
	SurveyService surveyservice;
	
	
	@PostMapping("createSurvey")
	public ResponseEntity<SurveyToQuestionBean> createSurvey(@RequestBody SurveyDetailsTable SDT){

		SurveyToQuestionBean saveSurvey=surveyservice.generateSurvey(SDT);
		return ResponseEntity.ok(saveSurvey);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<SurveyDetailsTable> getSurveybyId(@PathVariable Integer id){
		SurveyDetailsTable s=surveyservice.getSurveybyId(id);
		
		return new ResponseEntity<SurveyDetailsTable>(s,HttpStatus.OK); 
	}
	
	
	
	
	
}
