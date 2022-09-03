package com.masterdata.service.masterdataserviceclient.client.genericClient;

import com.masterdata.service.masterdataserviceclient.QueryRequest;

import java.util.List;

public interface GenericClient<DTO, ID> {
    DTO create(DTO createDTO);
    Boolean update(DTO updateDTO);
    Boolean delete(ID id);
    List<DTO> query(QueryRequest request);
}
