package com.boot.survey.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boot.survey.model.UserTable;

@Repository
public interface UserRepo extends JpaRepository<UserTable, Integer>{

	UserTable findByuserName(String userName);

	 Optional<UserTable> findByemail(String email);
}
