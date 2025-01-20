package com.boot.survey.exception_handle;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import jakarta.servlet.http.HttpServletRequest;


@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger logging =LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
	    // Log the full exception for debugging
	    logging.error("DataIntegrityViolationException occurred: {}", ex.getMessage());

	    String message = ex.getMessage();

	    // Handle duplicate key violations
	    if (message.contains("duplicate key value")) {
	        String attribute = extractDuplicateKeyDetails(message);
	        logging.error("Duplicate key violation for attribute: {}", attribute);
	        return ResponseEntity.status(HttpStatus.CONFLICT)
	                .body("Error: The " + attribute + " already exists. Please choose a different value.");
	    }

	    // Handle foreign key constraint violations
	    if (message.contains("foreign key constraint")) {
	        String detailedMessage = extractForeignKeyDetails(message);
	        logging.error("Foreign key constraint violation: {}", detailedMessage);
	        return ResponseEntity.status(HttpStatus.CONFLICT)
	                .body("Error: Unable to delete the entry because it is referenced by other records. " + detailedMessage);
	    }

	    // Generic error handling
	    logging.error("Unhandled database error: {}", message);
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	            .body("A database error occurred. Please contact support.");
	}

//	@ExceptionHandler(NoSuchElementException.class)
//    public ResponseEntity<String> handleNotFoundException(NoSuchElementException ex){
//    	    	
//    	return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                .body("Error:"+ex.getMessage());
//    }
    
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<Map<String, Object>> handleNotFoundException(NoSuchElementException ex) {
	    // Log the exception for debugging and tracking
	    logging.error("NoSuchElementException occurred: {}", ex.getMessage());

	    // Build a structured response
	    Map<String, Object> response = new HashMap<>();
	    response.put("timestamp", LocalDateTime.now());
	    response.put("status", HttpStatus.NOT_FOUND.value());
	    response.put("error", "Resource Not Found");
	    response.put("message", ex.getMessage());
	    response.put("path", getRequestPath());

	    return ResponseEntity.status(HttpStatus.NOT_FOUND)
	            .body(response);
	}

    
    
    
    private String extractDuplicateKeyDetails(String errorMessage) {
        try {
            // Extract the attribute from the error message (e.g., "(email)")
            String attribute = errorMessage.split("\\(")[1].split("\\)")[0];
            return attribute;
        } catch (Exception e) {
            // Fallback if parsing fails
            logging.warn("Failed to parse duplicate key details: {}", e.getMessage());
            return "attribute";
        }
    }

    
    private String extractForeignKeyDetails(String errorMessage) {
        try {
            // Extract the table and column details from the error message
            String table = errorMessage.split("table \"")[1].split("\"")[0];
            String column = errorMessage.split("Key \\(")[1].split("\\)")[0];
            return "Table: " + table + ", Column: " + column + ".";
        } catch (Exception e) {
            // Fallback if parsing fails
            logging.warn("Failed to parse foreign key details: {}", e.getMessage());
            return "Please check database relationships.";
        }
    }
    
    @Autowired
    private HttpServletRequest request;

    private String getRequestPath() {
        return request.getRequestURI();
    }


    
}
