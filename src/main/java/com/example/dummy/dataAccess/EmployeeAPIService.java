package com.example.dummy.dataAccess;

import com.example.dummy.dto.EmployeeDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class EmployeeAPIService {

    @Value("${employee.api.baseurl}")
    private String baseUrl;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public EmployeeAPIService (RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public List<EmployeeDTO> getAllEmployees() {

        String url = baseUrl + "/employees";

        try
        {
            String jsonResponse = restTemplate.getForObject(url, String.class);
            JsonNode jsonNode = objectMapper.readTree(jsonResponse);
            JsonNode dataNode = jsonNode.get("data");
            return objectMapper.readValue(dataNode.toString(), new TypeReference<>() {});
        }
        catch (HttpClientErrorException e) {
            int statusCode = e.getRawStatusCode();
            String statusText = e.getStatusText();
            System.err.println("Client error - Status Code: " + statusCode + ", Status Text: " + statusText);
            throw new HttpClientErrorException(e.getStatusCode(), statusText);
        }
        catch (Exception e) {
            throw new RuntimeException("Error while deserializing JSON response", e);
        }
    }

    public EmployeeDTO getEmployeeById(long id) {
        String url = baseUrl + "/employee/" + id;
        try{

            String jsonResponse = restTemplate.getForObject(url, String.class);
            return parseEmployeeDTO(jsonResponse);

        } catch (HttpClientErrorException e) {
            int statusCode = e.getRawStatusCode();
            String statusText = e.getStatusText();
            System.err.println("Client error - Status Code: " + statusCode + ", Status Text: " + statusText);
            throw new HttpClientErrorException(e.getStatusCode(), statusText);
        }
    }

    private EmployeeDTO parseEmployeeDTO(String jsonResponse) {
         try{
            JsonNode jsonNode = objectMapper.readTree(jsonResponse);
            JsonNode dataNode = jsonNode.get("data");
            return objectMapper.readValue(dataNode.toString(), new TypeReference<>() {});

        }catch (Exception e) {
            throw new RuntimeException("Error while deserializing JSON response", e);
        }
    }


}

