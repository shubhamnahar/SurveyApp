package com.boot.survey.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.boot.survey.dao.QuestionRepo;
import com.boot.survey.model.QuestionTable;
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
	
	
	
}
