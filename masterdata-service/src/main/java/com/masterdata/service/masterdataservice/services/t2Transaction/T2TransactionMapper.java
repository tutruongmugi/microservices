package com.masterdata.service.masterdataservice.services.t2Transaction;

import com.masterdata.service.masterdataservice.entities.T2Transaction;
import com.masterdata.service.masterdataservice.services.genericService.GenericMapper;
import com.masterdata.service.masterdataserviceclient.client.t2Transaction.T2TransactionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface T2TransactionMapper extends GenericMapper<T2Transaction, T2TransactionDTO> {
}
