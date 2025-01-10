/* ResponseDetail
 * response_detail_id (Primary Key)
 * response_id (Foreign Key to response_id)
 * question_id (Foreign Key to question_id)
 * answer (Stores the user's answer; could be text, option ID, or a rating depending on the question_type)
 */

package com.boot.survey.model;
import jakarta.persistence.*;

@Entity
public class ResponseDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long responseDetailId;

    @ManyToOne
    @JoinColumn(name = "response_id")
    private Response response;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private QuestionTable questiontable;

    private String answer;

    
	public Long getResponseDetailId() {
		return responseDetailId;
	}

	public void setResponseDetailId(Long responseDetailId) {
		this.responseDetailId = responseDetailId;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public QuestionTable getQuestiontable() {
		return questiontable;
	}

	public void setQuestiontable(QuestionTable questiontable) {
		this.questiontable = questiontable;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "ResponseDetail [responseDetailId=" + responseDetailId + ", response=" + response + ", questiontable="
				+ questiontable + ", answer=" + answer + "]";
	}
    
    

}
