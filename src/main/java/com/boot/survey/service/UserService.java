package com.boot.survey.service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

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

	public UserTable getUserbyEmail(String email) {
		
		return userRepo.findByemail(email)
				.orElseThrow(() -> new NoSuchElementException("User not found with email id : " + email));
//		return user;
	}

	public UserTable getUserbyId(Integer id) {
		
		UserTable user=userRepo.findById(id)
				.orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
		return user;
	}

	public void deleteUserbyId(Integer id) {
		userRepo.findById(id)
				.orElseThrow(() -> new NoSuchElementException("User does not Exist."));
		userRepo.deleteById(id);
//		return null;
	}

	public UserTable UpdateUserInfo(Integer id, UserTable updatedData) {
		// Find the existing user by ID
        UserTable existingUser = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));

        // Update only the provided fields
        if (updatedData.getUserName() != null) {
            existingUser.setUserName(updatedData.getUserName());
        }
        if (updatedData.getFirstName() != null) {
            existingUser.setFirstName(updatedData.getFirstName());
        }
        if (updatedData.getLastName() != null) {
            existingUser.setLastName(updatedData.getLastName());
        }
        if (updatedData.getEmail() != null) {
            existingUser.setEmail(updatedData.getEmail());
        }
        if (updatedData.getUser_psw() != null) {
            existingUser.setUser_psw(updatedData.getUser_psw());
        }
        if (updatedData.getPhone_number() != null) {
            existingUser.setPhone_number(updatedData.getPhone_number());
        }
        if (updatedData.getGender() != null) {
            existingUser.setGender(updatedData.getGender());
        }
        if (updatedData.getDOB() != null) {
            existingUser.setDOB(updatedData.getDOB());
        }
        if (updatedData.getRoles() != null) {
            existingUser.setRoles(updatedData.getRoles());
        }

        // Save the updated user
        return userRepo.save(existingUser);

	}

	

}
