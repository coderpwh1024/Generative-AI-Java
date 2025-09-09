package com.coderpwh.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.util.pattern.PathPattern;

/**
 * @author coderpwh
 */
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErrorResponse error = new ErrorResponse("Invalid_Input", "Invalid input parameter: " + ex.getMessage(), "Please check your input values and try again. Use the help() tool for examples.");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }




    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception e){
        ErrorResponse error = new ErrorResponse(
                "Internal_Error",
                "An unexpected error occurred: " + e.getMessage(),
                "Please try again later or contact support if the issue persists. Check server logs for details.");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }



    public static class ErrorResponse {

        private String code;

        private String message;

        private String resolution;


        public ErrorResponse(String code, String message, String resolution) {
            this.code = code;
            this.message = message;
            this.resolution = resolution;
        }

        // Getters required for JSON serialization
        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public String getResolution() {
            return resolution;
        }



    }

}
