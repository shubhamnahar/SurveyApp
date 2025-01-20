package com.boot.survey.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.survey.dao.SurveyDetailsRepo;
import com.boot.survey.dao.UserRepo;
import com.boot.survey.model.SurveyDetailsTable;
import com.boot.survey.model.UserTable;
import com.boot.survey.responseBeans.SurveyToQuestionBean;

import jakarta.transaction.Transactional;

@Service
public class SurveyService {

	@Autowired
	private SurveyDetailsRepo SDR;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	SurveyToQuestionBean ResponseBean;
	
	@Transactional
	public SurveyToQuestionBean generateSurvey(SurveyDetailsTable SDT) {

		SDT.setCreateddatetime(LocalDateTime.now());
		SDT.setModifieddatetime(LocalDateTime.now());
		String name =SDT.getUsertable().getUserName();
		UserTable user1= userRepo.findByuserName(name);
		SDT.setUsertable(user1);
		
		SurveyDetailsTable savedSurvey=SDR.save(SDT);
		ResponseBean.setSurvey_Name(savedSurvey.getSurvey_name());
		ResponseBean.setSurvey_id(savedSurvey.getSurvey_id());
		return ResponseBean;
	}

	public List<SurveyDetailsTable> getAllSurveys() {
		try {
			return SDR.findAll();
		}
		catch(Exception e) {
			System.out.println("here.......");
		}
		return null;
	}

	public SurveyDetailsTable getSurveybyId(Integer id) {
		
		SurveyDetailsTable s=SDR.findById(id) 
				.orElseThrow(() -> new NoSuchElementException("Survey not found with id: " + id));
		return s;
	}

}
