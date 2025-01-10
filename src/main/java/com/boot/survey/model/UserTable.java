package com.boot.survey.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.ColumnTransformer;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

/*
 * ----UserTable
 * user_id (Primary Key)
 * userName
 * firstName
 * lastName
 * email
 * user_psw
 * createddatetime
 * Roles
 * createdby
*/

@Entity
public class UserTable {

	@Id    //Specify the primary key of the table
	@GeneratedValue(strategy = GenerationType.IDENTITY)  //Creates a sequence which auto increment while inserting in table
	private Integer user_id;
	
	@Column(unique = true) //for making Unique Column 
	@ColumnTransformer(write = "LOWER(?)") 
	private String userName;
	
	private String firstName;
	private String lastName;
	
	@Column(unique = true)
	@ColumnTransformer(write = "LOWER(?)") 
	private String email;
	private String user_psw;
	
	private LocalDateTime createddatetime;
	private String roles;
	
	private String createdby;

	
	@OneToMany(mappedBy = "usertable", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<SurveyDetailsTable> surveydetailstable;
	
	
	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUser_psw() {
		return user_psw;
	}

	public void setUser_psw(String user_psw) {
		this.user_psw = user_psw;
	}

	public LocalDateTime getCreateddatetime() {
		return createddatetime;
	}

	public void setCreateddatetime(LocalDateTime createddatetime) {
		this.createddatetime = createddatetime;
	}

	
	public List<SurveyDetailsTable> getSurveydetailstable() {
		return surveydetailstable;
	}

	public void setSurveydetailstable(List<SurveyDetailsTable> surveydetailstable) {
		this.surveydetailstable = surveydetailstable;
	}

	
	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

//	@Override
//	public String toString() {
//		return "UserTable [user_id=" + user_id + ", userName=" + userName + ", firstName=" + firstName + ", lastName="
//				+ lastName + ", email=" + email + ", user_psw=" + user_psw + ", createddatetime=" + createddatetime
//				+ ", roles=" + roles + ", createdby=" + createdby + ", surveydetailstable=" + surveydetailstable + "]";
//	}
	
	@Override
	public String toString() {
	    return "UserTable [user_id=" + user_id 
	        + ", userName=" + userName 
	        + ", firstName=" + firstName 
	        + ", lastName=" + lastName 
	        + ", email=" + email 
	        + ", createddatetime=" + createddatetime 
	        + ", roles=" + roles 
	        + ", createdby=" + createdby 
	        + ", surveydetailstable_count=" + (surveydetailstable != null ? surveydetailstable.size() : 0) + "]";
	}

	
	
	
}
