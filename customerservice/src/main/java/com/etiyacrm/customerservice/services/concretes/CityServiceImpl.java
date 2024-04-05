package com.etiyacrm.customerservice.services.concretes;


import com.etiyacrm.customerservice.core.business.paging.PageInfo;
import com.etiyacrm.customerservice.core.responses.GetListResponse;
import com.etiyacrm.customerservice.dataAccess.abstracts.CityRepository;
import com.etiyacrm.customerservice.entities.City;
import com.etiyacrm.customerservice.mappers.CityMapper;
import com.etiyacrm.customerservice.services.abstracts.CityService;
import com.etiyacrm.customerservice.services.dtos.requests.cities.CreateCityRequest;
import com.etiyacrm.customerservice.services.dtos.requests.cities.UpdateCityRequest;
import com.etiyacrm.customerservice.services.dtos.responses.cities.*;
import com.etiyacrm.customerservice.services.rules.CityBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService {
    private CityRepository cityRepository;
    private CityBusinessRules cityBusinessRules;
    @Override
    public GetListResponse<GetAllCityResponse> findAll(PageInfo pageInfo) {
        //Aşağıdaki ifade der ki her bir city için CityMapper'ını kullanarak onun instancesini al
        //oradaki getAllCityResponseFromCity'yi kullan her bir city için o maplemeyi yap ve bunun contentini al

        Pageable pageable = PageRequest.of(pageInfo.getPage(), pageInfo.getSize());
        Page<City> cities =  cityRepository.findAllNotDeleted(pageable);

        GetListResponse<GetAllCityResponse> response = CityMapper.INSTANCE.getAllCityResponseFromCity(cities);
        response.setHasNext(cities.hasNext());
        response.setHasPrevious(cities.hasPrevious());
        return response;

//        GetListResponse<GetAllCityResponse> response = new GetListResponse<>();
//        response.setItems(cities.stream()
//                .map(CityMapper.INSTANCE::getAllCityResponseFromCity)
//                .collect(Collectors.toList()));
//        response.setTotalElements(cities.getTotalElements());
//        response.setTotalPages(pageInfo.getPage());
//        response.setSize(pageInfo.getSize());
//        response.setHasNext(cities.hasNext());
//        response.setHasPrevious(cities.hasPrevious());
//        return response;
    }

    @Override
    public GetCityResponse findById(long id) {
        return null;
    }

    @Override
    public UpdatedCityResponse update(UpdateCityRequest updateCityRequest, long id) {
        return null;
    }

    @Override
    public DeletedCityResponse delete(long id) {
        return null;
    }

    @Override
    public CreatedCityResponse add(CreateCityRequest createCityRequest) {
        cityBusinessRules.CityNameCanNotBeDuplicatedWhenInserted(createCityRequest.getName());
        City city = CityMapper.INSTANCE.cityFromCreateCityRequest(createCityRequest);
        City createdCity = cityRepository.save(city);

        CreatedCityResponse createdCityResponse = CityMapper.INSTANCE.createdCityResponseFromCity(createdCity);

        return  createdCityResponse;
    }

}
