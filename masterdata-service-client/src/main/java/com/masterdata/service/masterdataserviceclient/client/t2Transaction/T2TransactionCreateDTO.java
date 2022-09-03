package com.masterdata.service.masterdataserviceclient.client.t2Transaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties({"id","createdDate","version"})
public class T2TransactionCreateDTO extends T2TransactionDTO{
}
