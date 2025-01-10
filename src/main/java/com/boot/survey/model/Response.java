package com.boot.survey.model;

/*
 * Response
 * responseId (Primary Key, unique identifier for a response, e.g., UUID)
 * survey_id (Foreign Key to survey_id)
 * submitted_at
*/

import java.time.LocalDateTime;
import java.util.UUID;
import jakarta.persistence.*;

@Entity
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID responseId;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private SurveyDetailsTable surveydetailstable;

    private LocalDateTime submittedAt;

    
	public UUID getResponseId() {
		return responseId;
	}

	public void setResponseId(UUID responseId) {
		this.responseId = responseId;
	}

	
	
	public SurveyDetailsTable getSurveydetailstable() {
		return surveydetailstable;
	}

	public void setSurveydetailstable(SurveyDetailsTable surveydetailstable) {
		this.surveydetailstable = surveydetailstable;
	}

	public LocalDateTime getSubmittedAt() {
		return submittedAt;
	}

	public void setSubmittedAt(LocalDateTime submittedAt) {
		this.submittedAt = submittedAt;
	}

	@Override
	public String toString() {
		return "Response [responseId=" + responseId + ", surveydetailstable=" + surveydetailstable + ", submittedAt="
				+ submittedAt + "]";
	}
    
    
    

}
