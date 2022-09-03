package com.masterdata.service.masterdataserviceclient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public abstract class BaseDTO<ID> {
    private ID id;
}
