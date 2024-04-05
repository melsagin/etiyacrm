package com.etiyacrm.customerservice.services.abstracts;

import com.etiyacrm.customerservice.core.business.paging.PageInfo;
import com.etiyacrm.customerservice.core.responses.GetListResponse;
import com.etiyacrm.customerservice.services.dtos.requests.cities.CreateCityRequest;
import com.etiyacrm.customerservice.services.dtos.requests.cities.UpdateCityRequest;
import com.etiyacrm.customerservice.services.dtos.responses.cities.*;

import java.util.List;

public interface CityService {
    CreatedCityResponse add(CreateCityRequest createCityRequest);
    GetListResponse<GetAllCityResponse> findAll(PageInfo pageInfo); //her bir sayfada onarlı  olacak şekilde veri çekmek istiyorum bu yüzden PageInfo parametre olarak yazıldı
    GetCityResponse findById(long id);
    UpdatedCityResponse update(UpdateCityRequest updateCityRequest, long id);
    DeletedCityResponse delete(long id);

}