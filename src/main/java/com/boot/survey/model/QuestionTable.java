package com.boot.survey.model;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
/*
---questiontable
question_id (Primary Key)
survey_id (Foreign Key to survey_id)
question_title
question_type (e.g., multiple-choice, text, rating, etc.)
is_required (Boolean)
created_by
order_by

*/

@Entity
@Table(name="questiontable")
public class QuestionTable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer question_id;
	
	@ManyToOne
	@JoinColumn(name="survey_id")
	private SurveyDetailsTable surveydetailstable;
	
	
	private String question_title;
	private String question_type;
	private int order_by;
	private boolean is_required;
	
	@ManyToOne
	@JoinColumn(name="created_by")
	private UserTable usertable;

	@OneToMany(mappedBy = "questiontable", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
    private List<Options> options = new ArrayList<>();
	
	public Integer getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(Integer question_id) {
		this.question_id = question_id;
	}

	public SurveyDetailsTable getSurveydetailstable() {
		return surveydetailstable;
	}

	public void setSurveydetailstable(SurveyDetailsTable surveydetailstable) {
		this.surveydetailstable = surveydetailstable;
	}

	public String getQuestion_title() {
		return question_title;
	}

	public void setQuestion_title(String question_title) {
		this.question_title = question_title;
	}

	public String getQuestion_type() {
		return question_type;
	}

	public void setQuestion_type(String question_type) {
		this.question_type = question_type;
	}

	public boolean getIs_required() {
		return is_required;
	}

	public void setIs_required(boolean is_required) {
		this.is_required = is_required;
	}

	public UserTable getUsertable() {
		return usertable;
	}

	public void setUsertable(UserTable usertable) {
		this.usertable = usertable;
	}

	
	public List<Options> getOptions() {
		return options;
	}

	public void setOptions(List<Options> options) {
		this.options = options;
	}

	public int getOrder_by() {
		return order_by;
	}

	public void setOrder_by(int order_by) {
		this.order_by = order_by;
	}

	@Override
	public String toString() {
		return "QuestionTable [question_id=" + question_id + ", surveydetailstable=" + surveydetailstable
				+ ", question_title=" + question_title + ", question_type=" + question_type + ", is_required="
				+ is_required +", order_by="+order_by+ ", usertable_id= "+ (usertable != null ? usertable.getUser_id() : "null")+", options=" + (options != null ? options.size() : 0) + "]";
	}
	
	
	
	
	
	

}
