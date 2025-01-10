package com.boot.survey.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boot.survey.model.Options;

@Repository
public interface OptionRepository extends JpaRepository<Options, Integer> {
}
