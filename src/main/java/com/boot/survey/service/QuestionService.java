package com.boot.survey.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.survey.dao.QuestionRepo;
import com.boot.survey.dao.SurveyDetailsRepo;
import com.boot.survey.dao.UserRepo;
import com.boot.survey.model.Options;
import com.boot.survey.model.QuestionTable;
import com.boot.survey.model.SurveyDetailsTable;
import com.boot.survey.model.UserTable;
import com.boot.survey.requestBeans.OptionRequest;
import com.boot.survey.requestBeans.QuestionRequest;

import jakarta.transaction.Transactional;

@Service
public class QuestionService {

	@Autowired
    private QuestionRepo questionRepository;

    @Autowired
    private SurveyDetailsRepo surveyDetailsRepository;

    @Autowired
    private UserRepo userRepository;

    @Transactional
    public String saveQuestion(QuestionRequest request) {
        QuestionTable question = new QuestionTable();

        // Set basic question fields
        question.setQuestion_title(request.getQuestionTitle());
        question.setQuestion_type(request.getQuestionType());
        question.setIs_required(request.getisRequired());

        // Set SurveyDetailsTable reference
        if (request.getSurveyId() != null) {
            SurveyDetailsTable survey = surveyDetailsRepository.findById(request.getSurveyId())
                .orElseThrow(() -> new NoSuchElementException("Survey not found with ID:"+request.getSurveyId()));
            question.setSurveydetailstable(survey);
        }

        // Set UserTable reference
        if (request.getCreatedBy() != null) {
            UserTable user = userRepository.findByuserName(request.getCreatedBy());
            if (user == null) {
                throw new NoSuchElementException("User not found with username: " + request.getCreatedBy());
            }
            question.setUsertable(user);
        }

        // Handle Options (only for question types requiring them)
        if (requiresOptions(request.getQuestionType()) && request.getOptions() != null && !request.getOptions().isEmpty()) {
            List<Options> options = request.getOptions().stream().map(optReq -> {
                Options option = new Options();
                option.setOptions_title(optReq.getOptionsTitle());
                option.setQuestiontable(question); // Link option to question
                return option;
            }).collect(Collectors.toList());

            question.setOptions(options);
        }

        questionRepository.save(question);
        return "Success";
        		

    }

    private boolean requiresOptions(String type) {
        return "SingleChoice".equalsIgnoreCase(type) || "MultipleChoice".equalsIgnoreCase(type) || "Dropdown".equalsIgnoreCase(type);
    }

	public QuestionRequest viewQuestion(Integer question_id) {
		
		QuestionTable question =questionRepository.findById(question_id)
				.orElseThrow(() -> new NoSuchElementException("No Question found with the Question id: " + question_id));
		
		QuestionRequest Question_Request = new QuestionRequest();
		Question_Request.setQuestion_id(question.getQuestion_id());
		Question_Request.setQuestionTitle(question.getQuestion_title());
		Question_Request.setQuestionType(question.getQuestion_type());
		Question_Request.setRequired(question.getIs_required());
		Question_Request.setCreatedBy(question.getUsertable().getUserName());
		Question_Request.setSurveyId(question.getSurveydetailstable().getSurvey_id());
		
		if (requiresOptions(question.getQuestion_type()) && question.getOptions() != null && !question.getOptions().isEmpty()) {
            List<OptionRequest> options = question.getOptions().stream().map(optReq -> {
            	OptionRequest option = new OptionRequest();
                option.setOptionsTitle(optReq.getOptions_title());
//                option.setQuestiontable(question); // Link option to question
                return option;
            }).collect(Collectors.toList());

            Question_Request.setOptions(options);
        }
		
		return Question_Request;
	}

	public void deleteQuestion(Integer question_id) {
		
		QuestionTable question = questionRepository.findById(question_id)
                .orElseThrow(() -> new NoSuchElementException("Question not found with ID: " + question_id));

        // Delete the question (cascade will handle associated options)
		questionRepository.delete(question);
		
	}
}
