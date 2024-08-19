package com.selftechlearner.mhr.exception;

import com.selftechlearner.mhr.model.ApiResponse;
import com.selftechlearner.mhr.util.MedicalHealthRecordUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@RestControllerAdvice
public class MedicalHealthRecordExceptionHandler {

    @ExceptionHandler(MedicalHealthRecordException.class)
    public ResponseEntity<ProblemDetail> handleMedicalHealthRecordException(MedicalHealthRecordException ex, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        String requestURL = request.getRequestURL().toString();
        problemDetail.setType(URI.create(requestURL));
        problemDetail.setTitle(ex.getMessage());
        return new ResponseEntity<>(problemDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<ApiResponse> handleMedicalHealthRecordNotFoundException(RecordNotFoundException e) {

        ApiResponse response = MedicalHealthRecordUtil.createFailureMedicalHealthResponse(null, e.getMessage());
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatusCode.valueOf(404));
        problemDetail.setProperty("error", response);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
