package com.boot.survey.requestBeans;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuestionRequest {

	private String questionTitle;
    private String questionType;
    @JsonProperty("isRequired")
    private boolean isRequired;
    private Integer surveyId;
    private String createdBy;
    private List<OptionRequest> options = new ArrayList<>();
    
    
    
	public String getQuestionTitle() {
		return questionTitle;
	}
	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	public boolean isRequired() {
		return isRequired;
	}
	public void setRequired(boolean isRequired) {
		this.isRequired = isRequired;
	}
	public Integer getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public List<OptionRequest> getOptions() {
		return options;
	}
	public void setOptions(List<OptionRequest> options) {
		this.options = options;
	}
    
    
    
}
