package com.boot.survey.model;

/*
 * -----SurveyTable------
 * survey_id
 * survey_name
 * description
 * created_by (Foreign Key to user_id)
 * Createddatetime
 * Modifieddatetime
 * StartDate
 * EndDate

*/

import java.time.LocalDateTime;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class SurveyDetailsTable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "survey_id")
	private Integer surveyId;
	
	private String survey_name;
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "created_by", nullable = false)
	private UserTable usertable;
	
	private LocalDateTime Createddatetime;
	private LocalDateTime Modifieddatetime;
	
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime StartDate;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime EndDate;
	
	
	
	
	
	public Integer getSurvey_id() {
		return surveyId;
	}
	public void setSurvey_id(Integer surveyId) {
		this.surveyId = surveyId;
	}
	public String getSurvey_name() {
		return survey_name;
	}
	public void setSurvey_name(String survey_name) {
		this.survey_name = survey_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UserTable getUsertable() {
		return usertable;
	}
	public void setUsertable(UserTable user1) {
		this.usertable = user1;
	}
	public LocalDateTime getCreateddatetime() {
		return Createddatetime;
	}
	public void setCreateddatetime(LocalDateTime createddatetime) {
		Createddatetime = createddatetime;
	}
	public LocalDateTime getModifieddatetime() {
		return Modifieddatetime;
	}
	public void setModifieddatetime(LocalDateTime modifieddatetime) {
		Modifieddatetime = modifieddatetime;
	}
	public LocalDateTime getStartDate() {
		return StartDate;
	}
	public void setStartDate(LocalDateTime startDate) {
		StartDate = startDate;
	}
	public LocalDateTime getEndDate() {
		return EndDate;
	}
	public void setEndDate(LocalDateTime endDate) {
		EndDate = endDate;
	}
	
	
//	@Override
//	public String toString() {
//		return "SurveyTable [survey_id=" + survey_id + ", survey_name=" + survey_name + ", description=" + description
//				+ ", usertable=" + usertable + ", Createddatetime=" + Createddatetime + ", Modifieddatetime="
//				+ Modifieddatetime + ", StartDate=" + StartDate + ", EndDate=" + EndDate + "]";
//	}
	
	@Override
	public String toString() {
	    return "SurveyDetailsTable [survey_id=" + surveyId 
	        + ", survey_name=" + survey_name 
	        + ", description=" + description 
	        + ", usertable_id=" + (usertable != null ? usertable.getUser_id() : "null")
	        + ", Createddatetime=" + Createddatetime 
	        + ", Modifieddatetime=" + Modifieddatetime 
	        + ", StartDate=" + StartDate 
	        + ", EndDate=" + EndDate + "]";
	}

	
	


}
