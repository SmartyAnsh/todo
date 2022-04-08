package io.educative.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NationalityService {

    private RestTemplate restTemplate;

    @Autowired
    public NationalityService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String predictNationality(String name) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        return this.restTemplate
                .exchange("https://api.nationalize.io?name={name}", HttpMethod.GET, requestEntity, String.class, name)
                .getBody();

    }

}
