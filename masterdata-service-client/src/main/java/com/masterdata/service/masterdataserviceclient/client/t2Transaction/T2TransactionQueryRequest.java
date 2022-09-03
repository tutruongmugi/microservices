package com.masterdata.service.masterdataserviceclient.client.t2Transaction;

import com.masterdata.service.masterdataserviceclient.QueryRequest;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor @NoArgsConstructor
@ToString(callSuper = true) @EqualsAndHashCode(callSuper = true)
public class T2TransactionQueryRequest extends QueryRequest {
    private String id;
    private String transactionType;
    private String name;
    private String flag;
//    private Date createdDate;
    private String createdBy ;
}
