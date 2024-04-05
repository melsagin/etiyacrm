package com.etiyacrm.customerservice.core.crossCusttingConcerns.exceptions.handlers;

import com.etiyacrm.customerservice.core.crossCusttingConcerns.exceptions.details.BusinessProblemDetails;
import com.etiyacrm.customerservice.core.crossCusttingConcerns.exceptions.details.ProblemDetails;
import com.etiyacrm.customerservice.core.crossCusttingConcerns.exceptions.details.ValidationProblemDetails;
import com.etiyacrm.customerservice.core.crossCusttingConcerns.exceptions.types.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice //try Burası tüm istekleri AOP tekniği kullanarak try catch içine koyar
public class GlobalExceptionHandler {
    @ExceptionHandler({BusinessException.class}) //catch
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BusinessProblemDetails handleBusinessException(BusinessException exception) {
        BusinessProblemDetails problemDetails = new BusinessProblemDetails();
        problemDetails.setDetail(exception.getMessage());
        return problemDetails;
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationProblemDetails handleValidationException(MethodArgumentNotValidException exception) {
        //reflection ile ilgili sınıfın ilgili alanlarında herhangi bir error durumu var mı onu geziyorum
        //burada name'in durumuna baktığımız için key olarak name fieldım value olarak kendi verdiğim hata mesajım döner
        //dönen hata resultlerinin field errrorlarını getir

        List<Map<String, String>> validationErrors = new ArrayList<>();

        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
            Map<String, String> validationError = new HashMap<>();
            validationError.put("field", fieldError.getField());
            validationError.put("message", fieldError.getDefaultMessage());
            validationErrors.add(validationError);
        });

        ValidationProblemDetails validationProblemDetails = new ValidationProblemDetails();
        validationProblemDetails.setErrors(validationErrors);
        return validationProblemDetails;

    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ProblemDetails handleException() {
        return new ProblemDetails("Unknown Error","Some error occurred."
                ,"https://etiya.com/exceptions/unknown","400");
    }
}
