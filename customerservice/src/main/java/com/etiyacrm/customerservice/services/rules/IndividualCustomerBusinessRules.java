package com.etiyacrm.customerservice.services.rules;

import com.etiyacrm.customerservice.core.business.abstracts.MessageService;
import com.etiyacrm.customerservice.core.crossCusttingConcerns.exceptions.types.BusinessException;
import com.etiyacrm.customerservice.dataAccess.abstracts.IndividualCustomerRepository;
import com.etiyacrm.customerservice.entities.IndividualCustomer;
import com.etiyacrm.customerservice.messages.Messages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class IndividualCustomerBusinessRules {
    private MessageService messageService;
    private IndividualCustomerRepository individualCustomerRepository;

    public void individualCustomerNationalityIdCannotBeDuplicated(String nationalityId) {
        Optional<IndividualCustomer> individualCustomer = individualCustomerRepository.findByNationalityId(nationalityId);
        if(individualCustomer.isPresent() && individualCustomer.get().getCustomer().getDeletedDate() == null){
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.NATIONALITY_ID_EXISTS));
        }
    }

    public void individualCustomerIdMustExist(long id) {
        Optional<IndividualCustomer> individualCustomer = individualCustomerRepository.findById(id);
        if(!individualCustomer.isPresent() || individualCustomer.get().getCustomer().getDeletedDate() != null) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.INDIVIDUAL_CUSTOMER_NOT_FOUND));
        }
    }

    /*public void deletedIndividualCustomer(long id) {
        IndividualCustomer individualCustomer = individualCustomerRepository.findById(id).orElse(null);
        if (individualCustomer.getCustomer().getDeletedDate() != null) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.INDIVIDUAL_CUSTOMER_IS_DELETED));
        }
    }*/
}