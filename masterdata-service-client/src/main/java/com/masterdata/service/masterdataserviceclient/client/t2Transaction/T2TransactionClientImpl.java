package com.masterdata.service.masterdataserviceclient.client.t2Transaction;

import com.masterdata.service.masterdataserviceclient.client.genericClient.GenericClientImpl;

public class T2TransactionClientImpl extends GenericClientImpl<T2TransactionDTO, String> {
    public T2TransactionClientImpl() {
        super(System.getenv().getOrDefault("app.url","http://localhost:8000/api/t2-transaction"));
    }
}
