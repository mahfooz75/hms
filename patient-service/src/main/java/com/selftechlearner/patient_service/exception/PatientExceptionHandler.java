package com.selftechlearner.patient_service.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@RestControllerAdvice
public class PatientExceptionHandler {
    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<ProblemDetail> onException(PatientNotFoundException ex, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        String requestURL = request.getRequestURL().toString();
        problemDetail.setType(URI.create(requestURL));
        problemDetail.setTitle(ex.getMessage());
        return new ResponseEntity<>(problemDetail, HttpStatus.NOT_FOUND);
    }
}
