package com.example.dummy.dataAccess;

import com.example.dummy.dto.EmployeeDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
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
        String jsonResponse = restTemplate.getForObject(url, String.class);

        try
        {
            JsonNode jsonNode = objectMapper.readTree(jsonResponse);
            JsonNode dataNode = jsonNode.get("data");
            List<EmployeeDTO> employees  = objectMapper
                    .readValue(dataNode.toString(), new TypeReference<List<EmployeeDTO>>() {});

            return employees;
        }
        catch (Exception e) {
            throw new RuntimeException("Error while deserializing JSON response", e);
        }

    }

    public EmployeeDTO getEmployeeById(long id) {
        String url = baseUrl + "/employee/" + id;
        String jsonResponse = restTemplate.getForObject(url, String.class);
        try
        {
            JsonNode jsonNode = objectMapper.readTree(jsonResponse);
            JsonNode dataNode = jsonNode.get("data");
            EmployeeDTO employee  = objectMapper
                    .readValue(dataNode.toString(), new TypeReference<EmployeeDTO>() {});

            return employee;
        }
        catch (Exception e) {
            throw new RuntimeException("Error while deserializing JSON response", e);
        }
    }


}

