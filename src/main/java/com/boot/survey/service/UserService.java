package com.boot.survey.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.survey.dao.UserRepo;
import com.boot.survey.model.UserTable;

import jakarta.transaction.Transactional;


@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Transactional
	public String CreateUser(UserTable usertable) {	

//			String plain_psw=usertable.getUser_psw();
//			String pw_hash = BCrypt.hashpw(plain_psw, BCrypt.gensalt());
//			usertable.setUser_psw(pw_hash);
			usertable.setCreateddatetime(LocalDateTime.now());
			userRepo.save(usertable);
			return "Success";		
	}

	

}
