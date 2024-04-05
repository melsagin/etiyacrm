package com.etiyacrm.customerservice.mappers;

import com.etiyacrm.customerservice.core.responses.GetListResponse;
import com.etiyacrm.customerservice.entities.City;
import com.etiyacrm.customerservice.services.dtos.requests.cities.CreateCityRequest;
import com.etiyacrm.customerservice.services.dtos.responses.cities.CreatedCityResponse;
import com.etiyacrm.customerservice.services.dtos.responses.cities.GetAllCityResponse;
import com.etiyacrm.customerservice.services.dtos.responses.cities.GetCityResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper
public interface CityMapper { //Repository gibi kendi instancesini kendi üretir.
    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    //GetAllCityResponse getAllCityResponseFromCity(City city);//sana bir City parametresi vereceğim sen onu GetAllCityResponse tipine çevir
    City cityFromCreateCityRequest(CreateCityRequest createCityRequest);
    CreatedCityResponse createdCityResponseFromCity(City city);

    @Mapping(source = "cities.content", target = "items")
//    @Mapping(source = "cities.totalPages", target = "totalPages")
    GetListResponse<GetAllCityResponse> getAllCityResponseFromCity(Page<City> cities);



}
