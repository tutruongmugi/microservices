package com.masterdata.service.masterdataservice.services.genericService;

import com.masterdata.service.masterdataservice.entities.BaseEntity;
import com.masterdata.service.masterdataserviceclient.ResponseDTO;
import com.masterdata.service.masterdataserviceclient.QueryRequest;

import java.util.List;

public interface GenericService<T extends BaseEntity, DTO, ID> {
    List<DTO> findAll();
    DTO findById(ID id);
    DTO save(DTO dto);
    Boolean update(DTO dto);
    Boolean deleteById(ID id);
    ResponseDTO query(QueryRequest queryRequest);
}
