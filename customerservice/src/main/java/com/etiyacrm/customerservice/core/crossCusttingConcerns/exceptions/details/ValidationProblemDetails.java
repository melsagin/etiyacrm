package com.etiyacrm.customerservice.core.crossCusttingConcerns.exceptions.details;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ValidationProblemDetails extends ProblemDetails {
    private List<Map<String, String>> errors;

    public ValidationProblemDetails() {
        setTitle("Validation Exception");
        setType("https://etiya.com/exceptions/validation");
        setStatus("400");
        setDetail("Validation Rule Problems");
    }
}
