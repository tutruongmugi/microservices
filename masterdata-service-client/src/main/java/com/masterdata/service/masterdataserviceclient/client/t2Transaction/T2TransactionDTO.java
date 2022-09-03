package com.masterdata.service.masterdataserviceclient.client.t2Transaction;

import com.masterdata.service.masterdataserviceclient.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor @AllArgsConstructor
public class T2TransactionDTO extends BaseDTO<String> {
    private String transactionType;
    private String name;
    private String flag;
    private Date createdDate;
    private String createdBy ;
    private String version;
}
