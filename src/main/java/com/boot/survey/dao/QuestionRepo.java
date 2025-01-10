package com.boot.survey.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boot.survey.model.QuestionTable;


@Repository
public interface QuestionRepo extends JpaRepository<QuestionTable, Integer>{

	List<QuestionTable> findBySurveydetailstable_SurveyId(Integer surveyId);


}
