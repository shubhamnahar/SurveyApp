/*
 * ParticipationDetails
 * participation_id (Primary Key)
 * user_id (Foreign Key to User Table)
 * survey_id (Foreign Key to Survey Table)
 * start_time (Timestamp for when the user starts the survey)
 * end_time (Timestamp for when the user submits the survey)
 * participation_status (Optional, e.g., IN_PROGRESS, COMPLETED)
*/
package com.boot.survey.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;


@Entity
public class ParticipationDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long participationId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserTable usertable;

    @ManyToOne
    @JoinColumn(name = "survey_id", nullable = false)
    private SurveyDetailsTable surveydetailstable;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    private ParticipationStatus participationStatus;

	public Long getParticipationId() {
		return participationId;
	}

	public void setParticipationId(Long participationId) {
		this.participationId = participationId;
	}

	public UserTable getUsertable() {
		return usertable;
	}

	public void setUsertable(UserTable usertable) {
		this.usertable = usertable;
	}

	public SurveyDetailsTable getSurveydetailstable() {
		return surveydetailstable;
	}

	public void setSurveydetailstable(SurveyDetailsTable surveydetailstable) {
		this.surveydetailstable = surveydetailstable;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public ParticipationStatus getParticipationStatus() {
		return participationStatus;
	}

	public void setParticipationStatus(ParticipationStatus participationStatus) {
		this.participationStatus = participationStatus;
	}

	@Override
	public String toString() {
		return "ParticipationDetails [participationId=" + participationId + ", usertable=" + usertable
				+ ", surveydetailstable=" + surveydetailstable + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", participationStatus=" + participationStatus + "]";
	}
    
    

}



