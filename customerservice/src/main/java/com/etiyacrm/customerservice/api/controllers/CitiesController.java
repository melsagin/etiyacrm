package com.etiyacrm.customerservice.api.controllers;

import com.etiyacrm.customerservice.core.business.paging.PageInfo;
import com.etiyacrm.customerservice.core.responses.GetListResponse;
import com.etiyacrm.customerservice.services.abstracts.CityService;
import com.etiyacrm.customerservice.services.dtos.requests.cities.CreateCityRequest;
import com.etiyacrm.customerservice.services.dtos.requests.cities.UpdateCityRequest;
import com.etiyacrm.customerservice.services.dtos.responses.cities.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customerservice/api/v1/cities")
@AllArgsConstructor
public class CitiesController {
    private CityService cityService;

    @PostMapping
    public CreatedCityResponse add(@RequestBody CreateCityRequest createCityRequest) {
        return cityService.add(createCityRequest);
    }

    @GetMapping
    public GetListResponse<GetAllCityResponse> findAll( PageInfo pageInfo){
        return cityService.findAll(pageInfo);
    }

    @GetMapping("/{id}")
    public GetCityResponse findById(@PathVariable long id) {
        return cityService.findById(id);
    }

    @PutMapping("/{id}")
    public UpdatedCityResponse update(
            @RequestBody UpdateCityRequest updateCityRequest, @PathVariable long id) {
        return cityService.update(updateCityRequest, id);
    }

    @DeleteMapping("/{id}")
    public DeletedCityResponse delete(@PathVariable long id) {
        return cityService.delete(id);
    }


}
