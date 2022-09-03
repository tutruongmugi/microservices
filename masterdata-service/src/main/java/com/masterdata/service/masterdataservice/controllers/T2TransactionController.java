package com.masterdata.service.masterdataservice.controllers;

import com.masterdata.service.masterdataservice.entities.T2Transaction;
import com.masterdata.service.masterdataservice.services.genericService.GenericService;
import com.masterdata.service.masterdataserviceclient.ResponseDTO;
import com.masterdata.service.masterdataserviceclient.client.t2Transaction.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/t2-transaction")
@Tag(name = "T2 Transaction", description = "T2 Transaction")
public class T2TransactionController {
    @Autowired
    private GenericService<T2Transaction, T2TransactionDTO, String> t2TransactionStringGenericService;

    @GetMapping( produces = "application/json")
    public ResponseEntity<T2TransactionQueryResponse> getAllT2Transaction(){
        List<T2TransactionDTO> data = t2TransactionStringGenericService.findAll();
        T2TransactionQueryResponse response = new T2TransactionQueryResponse();
        response.setData(data);
        return ResponseEntity.ok(response);
    }
    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<T2TransactionDTO> create(@RequestBody T2TransactionCreateDTO payload){
        T2TransactionDTO t2TransactionDTO = t2TransactionStringGenericService.save(payload);
        return ResponseEntity.ok(t2TransactionDTO);
    }

    @PutMapping(value = "/{id}",  consumes = "application/json")
    public ResponseEntity<String> update(@RequestBody T2TransactionUpdateDTO payload){
        if(t2TransactionStringGenericService.update(payload)){
            return ResponseEntity.ok("OK");
        }
        return new ResponseEntity<>("ID not found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable String id){
        if(t2TransactionStringGenericService.deleteById(id)){
            return ResponseEntity.ok("OK");
        }

         return new ResponseEntity<>("ID not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/query", produces = "application/json", consumes = "application/json")
    public ResponseEntity<ResponseDTO<T2TransactionDTO>> query(@RequestBody T2TransactionQueryRequest queryRequest){
        if(queryRequest.getSize() <= 0){
            return new ResponseEntity<>(new ResponseDTO<>(),HttpStatus.BAD_REQUEST);
        }
        ResponseDTO<T2TransactionDTO> responseDTO = t2TransactionStringGenericService.query(queryRequest);
        return ResponseEntity.ok(responseDTO);
    }
}
