package com.boot.survey.model;

import jakarta.persistence.*;
/*
Options
option_id (Primary Key)
question_id (Foreign Key to question_id)
Options_title
*/

@Entity
public class Options {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer option_id;
	
	@ManyToOne
	@JoinColumn(name="question_id")
	private QuestionTable questiontable;
	
	private String Options_title;

	public Integer getOption_id() {
		return option_id;
	}

	public void setOption_id(Integer option_id) {
		this.option_id = option_id;
	}

	public QuestionTable getQuestiontable() {
		return questiontable;
	}

	public void setQuestiontable(QuestionTable questiontable) {
		this.questiontable = questiontable;
	}

	public String getOptions_title() {
		return Options_title;
	}

	public void setOptions_title(String options_title) {
		Options_title = options_title;
	}

	@Override
	public String toString() {
		return "Options [option_id=" + option_id + ", Options_title="
				+ Options_title + ", questiontable=" + (questiontable != null ? questiontable.getQuestion_title() : "null") + "]";
	}
	
	
	
	
}
