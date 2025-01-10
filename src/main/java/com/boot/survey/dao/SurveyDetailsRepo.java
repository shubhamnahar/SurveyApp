package com.boot.survey.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boot.survey.model.SurveyDetailsTable;

@Repository
public interface SurveyDetailsRepo extends JpaRepository<SurveyDetailsTable, Integer>{

}
