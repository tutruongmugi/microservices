package com.masterdata.service.masterdataserviceclient.client.genericClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.masterdata.service.masterdataserviceclient.BaseDTO;
import com.masterdata.service.masterdataserviceclient.QueryRequest;
import com.masterdata.service.masterdataserviceclient.ResponseDTO;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class GenericClientImpl<DTO extends BaseDTO, ID> implements GenericClient<DTO, ID>{
    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private String url;

    public GenericClientImpl(String url) {
        this.url = url;
    }

    @Override
    public DTO create(DTO createDTO) {
        HttpPost httpPost = new HttpPost(url);
        StringEntity requestBody;
        try{
            String json = objectMapper.writeValueAsString(createDTO);
            requestBody = new StringEntity(json);
        } catch (JsonProcessingException | UnsupportedEncodingException e){
            throw new RuntimeException(e);
        }
        httpPost.setEntity(requestBody);
        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

        try(CloseableHttpResponse response = httpClient.execute(httpPost)){
            int httpStatus = response.getStatusLine().getStatusCode();

            HttpEntity entity = response.getEntity();
            Header header = entity.getContentType();

            if(entity != null && httpStatus == 200){
                String resultJsonString = EntityUtils.toString(entity, StandardCharsets.UTF_8);
                if(StringUtils.isNoneBlank(resultJsonString)){
                    BaseDTO result = objectMapper.readValue(resultJsonString,BaseDTO.class);
                    return (DTO) result;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        return null;
    }

    @Override
    public Boolean update(DTO updateDTO) {
        String newUrl = url +"/" +"id";
        HttpPut httpPut = new HttpPut(newUrl);
        StringEntity requestBody;
        try{
            String json = objectMapper.writeValueAsString(updateDTO);
            requestBody = new StringEntity(json);
        } catch (JsonProcessingException | UnsupportedEncodingException e){
            throw new RuntimeException(e);
        }
        httpPut.setEntity(requestBody);
        httpPut.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

        try(CloseableHttpResponse response = httpClient.execute(httpPut)){
            int httpStatus = response.getStatusLine().getStatusCode();


            if(  httpStatus == 200){
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        return false;
    }

    @Override
    public Boolean delete(ID id) {
        String newUrl = url +"/" +"id";
        HttpDelete httpDelete = new HttpDelete(newUrl);

        httpDelete.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

        try(CloseableHttpResponse response = httpClient.execute(httpDelete)){
            int httpStatus = response.getStatusLine().getStatusCode();


            if(  httpStatus == 200){
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        return false;
    }

    @Override
    public List<DTO> query(QueryRequest request) {
        String newUrl = url + "/query";
        HttpPost httpPost = new HttpPost(newUrl);
        StringEntity requestBody;
        try{
            String json = objectMapper.writeValueAsString(request);
            requestBody = new StringEntity(json);
        } catch (JsonProcessingException | UnsupportedEncodingException e){
            throw new RuntimeException(e);
        }
        httpPost.setEntity(requestBody);
        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

        try(CloseableHttpResponse response = httpClient.execute(httpPost)){
            int httpStatus = response.getStatusLine().getStatusCode();

            HttpEntity entity = response.getEntity();
            Header header = entity.getContentType();

            if(entity != null && httpStatus == 200){
                String resultJsonString = EntityUtils.toString(entity, StandardCharsets.UTF_8);
                if(StringUtils.isNoneBlank(resultJsonString)){
                    return objectMapper.readValue(resultJsonString, ResponseDTO.class).getData();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        return null;
    }
}
