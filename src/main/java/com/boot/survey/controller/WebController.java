package com.boot.survey.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.boot.survey.dao.QuestionRepo;
import com.boot.survey.model.QuestionTable;
import com.boot.survey.model.SurveyDetailsTable;
import com.boot.survey.service.SurveyService;

@Controller
public class WebController {
	
	@Autowired
	SurveyService surveyservice;
	
	@Autowired
	QuestionRepo questionrepo;
	
	@GetMapping("createUsers")
	public String createUser() {
		return "NewUsers.jsp";
	}
	
	@GetMapping("CreateSurvey")
	public String createSurvey() {
		return "CreateSurvey.jsp";
	}
	
	@GetMapping("editQuestions")
	public String editQuestions(@RequestParam Integer SurveyID,@RequestParam String SurveyName,Model model) {
		model.addAttribute("SurveyID", SurveyID);
		model.addAttribute("SurveyName", SurveyName);
		return "AddQuestions.jsp";
	}
	

	@GetMapping("getSurvey")
    public String getAllSurveys(Model model) {
        List<SurveyDetailsTable> surveys = surveyservice.getAllSurveys();
        surveys.forEach(survey -> System.out.println(survey.getUsertable().getUserName()));
        model.addAttribute("surveys", surveys);
        return "surveyList.jsp"; // JSP template
    }
	
	@GetMapping("/survey/{survey_id}/start")
	public String LoadSurvey(@PathVariable("survey_id") Integer survey_id,Model model) {
		  List<QuestionTable> questions = questionrepo.findBySurveydetailstable_SurveyId(survey_id);
	        model.addAttribute("questions", questions);
	        System.out.println("here ----"+questions);
	        return "/survey.jsp";
	}

}
