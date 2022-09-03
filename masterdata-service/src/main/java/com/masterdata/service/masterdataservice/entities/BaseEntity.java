package com.masterdata.service.masterdataservice.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;


@Getter @Setter
public abstract class BaseEntity<ID> {
    @Id
    private ID id;
}
