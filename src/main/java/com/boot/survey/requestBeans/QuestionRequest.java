package com.boot.survey.requestBeans;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuestionRequest {

	private Integer question_id;
	private String questionTitle;
    private String questionType;
    
    @JsonProperty("isRequired")
    private boolean isRequired;
    
    private Integer surveyId;
    private String createdBy;
    private List<OptionRequest> options = new ArrayList<>();
    
    
    
	public Integer getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(Integer question_id) {
		this.question_id = question_id;
	}
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
	public boolean getisRequired() {
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
	@Override
	public String toString() {
		return "QuestionRequest [question_id=" + question_id + ", questionTitle=" + questionTitle + ", questionType="
				+ questionType + ", isRequired=" + isRequired + ", surveyId=" + surveyId + ", createdBy=" + createdBy
				+ ", options=" + options + "]";
	}
    
    
    
}
