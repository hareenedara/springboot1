package com.refresh.samples.Controllers;

import com.refresh.samples.model.EmpValidError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class EmployeeControllerAdvisor {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public EmpValidError handleEmpValidation(ConstraintViolationException ex){
        EmpValidError error = new EmpValidError();
        List<String> temp = ex.getConstraintViolations().stream().map(it -> it.getMessage()).collect(Collectors.toList());
        error.setErrorMessages(temp);
        error.setStatus(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        return error;
    }
}
