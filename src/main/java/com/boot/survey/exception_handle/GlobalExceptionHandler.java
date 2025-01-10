package com.boot.survey.exception_handle;
/*----------------------------------------------------------------------------------------
 * ERROR if duplicate username is Entered-
"could not execute statement [ERROR: duplicate key value violates unique constraint "ukp50irg6kthpq3f33xu9r1kw4x"
Detail: Key (user_name)=(shubham) already exists.] [insert into user_table (createddatetime,email,first_name,roles,user_name,user_psw) values (?,?,?,?,?,?)]; SQL [insert into user_table (createddatetime,email,first_name,roles,user_name,user_psw) values (?,?,?,?,?,?)]; constraint [ukp50irg6kthpq3f33xu9r1kw4x]"
---------------------------------------------------------------------------------------------
 
 */
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        // Customize the error message
    	
    	System.out.println(ex.getMessage());
        if (ex.getMessage().contains("duplicate key value")) {
        	if(ex.getMessage().contains("(email)")) {
        		return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Error: The Email already exists. Please choose a different Email.");
        	}else if (ex.getMessage().contains("(user_name)"))
        		return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Error: The username already exists. Please choose a different username.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Database error occurred.");
    }
}
