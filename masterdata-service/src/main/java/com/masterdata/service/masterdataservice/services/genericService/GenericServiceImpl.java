package com.masterdata.service.masterdataservice.services.genericService;

import com.masterdata.service.masterdataservice.entities.BaseEntity;
import com.masterdata.service.masterdataservice.repositories.GenericRepository;
import com.masterdata.service.masterdataserviceclient.BaseDTO;
import com.masterdata.service.masterdataserviceclient.ResponseDTO;
import com.masterdata.service.masterdataserviceclient.QueryRequest;
import com.querydsl.core.types.Predicate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Getter @Setter
public abstract class GenericServiceImpl<T extends BaseEntity, DTO extends BaseDTO, ID> implements GenericService<T, DTO, ID> {
    private GenericRepository<T, ID> genericRepository;
    private GenericMapper<T, DTO> mapper;

    public GenericServiceImpl(GenericRepository<T, ID> genericRepository, GenericMapper<T, DTO> mapper) {
        this.genericRepository = genericRepository;
        this.mapper = mapper;
    }

    @Override
    public List<DTO> findAll() {
        List<T> listT = genericRepository.findAll();
        List<DTO> data = StreamSupport.stream(listT.spliterator(),false).map(item -> mapper.toDTO(item)).collect(Collectors.toList());
        return data;
    }

    @Override
    public DTO findById(ID id) {
        Optional<T> optionalT = genericRepository.findById(id);
        if(optionalT.isPresent()){
            DTO dto = mapper.toDTO(optionalT.get());
            return dto;
        }
        return null;
    }

    @Override
    public DTO save(DTO dto) {
        T entity = mapper.toEntity(dto);
        T savedEntity = genericRepository.save(entity);
        return mapper.toDTO(savedEntity);
    }

    @Override
    public Boolean update(DTO dto) {
        DTO db = this.findById((ID) dto.getId());
        if(db != null){
            T targetEntity = mapper.toEntity(db);
            T entity = mapper.toEntity(targetEntity, dto);
            genericRepository.save(entity);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteById(ID id) {
        DTO db = this.findById(id);
        if( db != null){
            genericRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public ResponseDTO query(QueryRequest queryRequest) {
        Predicate predicate = createPredicate(queryRequest);

        Page<T> pageData = genericRepository.findAll(predicate,PageRequest.of(queryRequest.getStart(), queryRequest.getSize()));
        List<DTO> data = StreamSupport.stream(pageData.getContent().spliterator(),false).map(item -> mapper.toDTO(item)).collect(Collectors.toList());


        ResponseDTO responseDTO = new ResponseDTO<T>();
        responseDTO.setStart(queryRequest.getStart());
        responseDTO.setSize(queryRequest.getSize());
        responseDTO.setTotal(pageData.getTotalElements());
        responseDTO.setData(data);

        return responseDTO;
    }

    public abstract Predicate createPredicate(QueryRequest queryRequest);
}
