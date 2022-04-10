package io.educative.unit;

import io.educative.services.NationalityRestTemplateService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(NationalityRestTemplateService.class)
public class NationalityRestTemplateServiceTest {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NationalityRestTemplateService nationalityRestTemplateService;

    private MockRestServiceServer server;

    @BeforeEach
    public void init() {
        server = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void givenServerIsMocked_whenPredictNationality_thenReceiveTheResponse() {
        this.server.expect(requestTo("https://api.nationalize.io?name=john"))
                .andRespond(withSuccess("{\"name\":\"john\",\"country\":[{\"country_id\":\"US\",\"probability\":0.048398225615958565},{\"country_id\":\"IM\",\"probability\":0.04438246053773764},{\"country_id\":\"IE\",\"probability\":0.042102085396037124}]}", MediaType.APPLICATION_JSON));

        String userServiceResponse = nationalityRestTemplateService.predictNationality("john");

        Assert.assertEquals("{\"name\":\"john\",\"country\":[{\"country_id\":\"US\",\"probability\":0.048398225615958565},{\"country_id\":\"IM\",\"probability\":0.04438246053773764},{\"country_id\":\"IE\",\"probability\":0.042102085396037124}]}", userServiceResponse);
    }

}
