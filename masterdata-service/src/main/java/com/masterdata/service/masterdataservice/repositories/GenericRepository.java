package com.masterdata.service.masterdataservice.repositories;

import com.masterdata.service.masterdataservice.entities.BaseEntity;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericRepository<T extends BaseEntity, ID> extends MongoRepository<T,ID>, QuerydslPredicateExecutor<T>{

}
