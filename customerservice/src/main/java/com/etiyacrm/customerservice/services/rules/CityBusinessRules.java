package com.etiyacrm.customerservice.services.rules;

import com.etiyacrm.customerservice.core.business.abstracts.MessageService;
import com.etiyacrm.customerservice.core.crossCusttingConcerns.exceptions.types.BusinessException;
import com.etiyacrm.customerservice.dataAccess.abstracts.CityRepository;
import com.etiyacrm.customerservice.entities.City;
import com.etiyacrm.customerservice.messages.Messages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CityBusinessRules {
    private CityRepository cityRepository;
    private MessageService messageService;

    public void  CityNameCanNotBeDuplicatedWhenInserted(String name){
        //Optional olması o datanın null da gelebileceğini söyler
        //Ignorecase küçük büyük harf duyarsız çalış demektir
        Optional<City> city = cityRepository.findByNameIgnoreCase(name);

        if(city.isPresent()){//böyle bir şehir zaten var ise throw gönderilir
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.CITY_NAME_EXISTS));
        }
    }
}
