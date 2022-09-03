package com.masterdata.service.masterdataserviceclient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class QueryRequest {
    private Integer start = 0;
    private Integer size = 20;
}
