package com.masterdata.service.masterdataservice.services.t2Transaction;

import com.masterdata.service.masterdataservice.entities.QT2Transaction;
import com.masterdata.service.masterdataservice.entities.T2Transaction;
import com.masterdata.service.masterdataservice.repositories.GenericRepository;
import com.masterdata.service.masterdataservice.repositories.T2TransactionRepository;
import com.masterdata.service.masterdataservice.services.genericService.GenericMapper;
import com.masterdata.service.masterdataservice.services.genericService.GenericServiceImpl;
import com.masterdata.service.masterdataserviceclient.QueryRequest;
import com.masterdata.service.masterdataserviceclient.client.t2Transaction.T2TransactionDTO;
import com.masterdata.service.masterdataserviceclient.client.t2Transaction.T2TransactionQueryRequest;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class T2TransactionServiceImpl extends GenericServiceImpl<T2Transaction, T2TransactionDTO, String> {
    public T2TransactionServiceImpl(T2TransactionRepository t2TransactionRepository, T2TransactionMapper mapper) {
        super(t2TransactionRepository, mapper);
    }

    @Override
    public Predicate createPredicate(QueryRequest queryRequest) {
        T2TransactionQueryRequest request = (T2TransactionQueryRequest) queryRequest;

        QT2Transaction query = QT2Transaction.t2Transaction;
        BooleanBuilder predicate = new BooleanBuilder();
        if(StringUtils.isNoneBlank(request.getId())){
            predicate.and(query.id.eq(request.getId()));
        }
        if(StringUtils.isNoneBlank(request.getTransactionType())){
            predicate.and(query.transactionType.eq(request.getTransactionType()));
        }
        if(StringUtils.isNoneBlank(request.getName())){
            predicate.and(query.name.eq(request.getName()));
        }
        if(StringUtils.isNoneBlank(request.getFlag())){
            predicate.and(query.flag.eq(request.getFlag()));
        }
        if(StringUtils.isNoneBlank(request.getCreatedBy())){
            predicate.and(query.createdBy.eq(request.getCreatedBy()));
        }

        return predicate;
    }
}
