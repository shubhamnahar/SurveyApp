package com.boot.survey.responseBeans;

import org.springframework.stereotype.Component;

@Component
public class SurveyToQuestionBean {

	private int Survey_id;
	private String Survey_Name;
	
	
	
	public int getSurvey_id() {
		return Survey_id;
	}
	public void setSurvey_id(int survey_id) {
		Survey_id = survey_id;
	}
	public String getSurvey_Name() {
		return Survey_Name;
	}
	public void setSurvey_Name(String survey_Name) {
		Survey_Name = survey_Name;
	}
	@Override
	public String toString() {
		return "SurveyToQuestionBean [Survey_id=" + Survey_id + ", Survey_Name=" + Survey_Name + "]";
	}
	
	
}
