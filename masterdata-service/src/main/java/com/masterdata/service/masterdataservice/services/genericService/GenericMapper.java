package com.masterdata.service.masterdataservice.services.genericService;

import com.masterdata.service.masterdataservice.entities.BaseEntity;
import com.masterdata.service.masterdataserviceclient.BaseDTO;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 *
 * @param <T> must be a concrete entity class
 * @param <DTO> must be a concrete dto class
 */
public interface GenericMapper<T extends BaseEntity, DTO extends BaseDTO> {
    T toEntity(DTO dto);
    DTO toDTO(T entity);

    @Mapping(target = "id", ignore = true)
    T toEntity(@MappingTarget T entity, DTO dto);
}
