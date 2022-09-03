package com.masterdata.service.masterdataservice.entities;


import com.querydsl.core.annotations.QueryEntity;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@QueryEntity
@Document("t2Transaction")
@Data
public class T2Transaction extends BaseEntity<String>{
    private String transactionType;
    private String name;
    private String flag;

    @CreatedDate
    private Date createdDate;
    private String createdBy = "system";

    @Version
    private String version;

}
