package com.etiyacrm.customerservice.api.controllers;

import com.etiyacrm.customerservice.services.abstracts.IndividualCustomerService;
import com.etiyacrm.customerservice.services.dtos.requests.individualCustomers.CreateIndividualCustomerRequest;
import com.etiyacrm.customerservice.services.dtos.requests.individualCustomers.UpdateIndividualCustomerRequest;
import com.etiyacrm.customerservice.services.dtos.responses.individualCustomers.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/individualCustomers")
public class IndividualCustomersController {

    private IndividualCustomerService individualCustomerService;

    @PostMapping
    public CreatedIndividualCustomerResponse add(@RequestBody CreateIndividualCustomerRequest createIndividualCustomerRequest) {
        return individualCustomerService.add(createIndividualCustomerRequest);
    }

    @GetMapping
    public List<GetAllIndividualCustomerResponse> findAll() {
        return individualCustomerService.findAll();
    }

    @GetMapping("/{id}")
    public GetIndividualCustomerResponse findById(@PathVariable long id) {
        return individualCustomerService.findById(id);
    }

    @PutMapping("/{id}")
    public UpdatedIndividualCustomerResponse update(
            @RequestBody UpdateIndividualCustomerRequest updateIndividualCustomerRequest, @PathVariable long id) {
        return individualCustomerService.update(updateIndividualCustomerRequest, id);
    }

    @DeleteMapping("/{id}")
    public DeletedIndividualCustomerResponse delete(@PathVariable long id) {
        return individualCustomerService.delete(id);
    }
}