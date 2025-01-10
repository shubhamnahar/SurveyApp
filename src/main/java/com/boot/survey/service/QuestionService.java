package com.boot.survey.service;

import java.util.List;
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
        question.setIs_required(request.isRequired());

        // Set SurveyDetailsTable reference
        if (request.getSurveyId() != null) {
            SurveyDetailsTable survey = surveyDetailsRepository.findById(request.getSurveyId())
                .orElseThrow(() -> new RuntimeException("Survey not found"));
            question.setSurveydetailstable(survey);
        }

        // Set UserTable reference
        if (request.getCreatedBy() != null) {
            UserTable user = userRepository.findByuserName(request.getCreatedBy());
            if (user == null) {
                throw new RuntimeException("User not found with username: " + request.getCreatedBy());
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
}
