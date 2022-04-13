package io.educative.integration;

import io.educative.services.NationalityWebClientService;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.*;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;

public class NationalityWebClientServiceIntegrationTest {

    private static MockWebServer webServer;

    private NationalityWebClientService nationalityService;

    @BeforeAll
    public static void init() throws IOException {
        webServer = new MockWebServer();
        webServer.start();
    }

    @AfterAll
    public static void tearDown() throws IOException {
        webServer.shutdown();
    }

    @BeforeEach
    public void initialize() {
        nationalityService = new NationalityWebClientService();
    }

    @Test
    public void givenWebServerIsMocked_whenPredictNationality_thenReceiveTheResponse() throws InterruptedException {
        String baseUrl = String.format("http://127.0.0.1:%s", webServer.getPort());

        String expectedResp = "{\"name\":\"john\",\"country\":[{\"country_id\":\"US\",\"probability\":0.048398225615958565},{\"country_id\":\"IM\",\"probability\":0.04438246053773764},{\"country_id\":\"IE\",\"probability\":0.042102085396037124}]}";

        webServer.enqueue(new MockResponse()
                .setBody(expectedResp)
                .addHeader("Content-Type", "application/json"));

        Mono<String> stringMono = nationalityService.predictNationality(baseUrl, "john");

        StepVerifier.create(stringMono)
                .expectNextMatches(resp -> resp.toLowerCase().equals(expectedResp.toLowerCase()))
                .verifyComplete();

        RecordedRequest recordedRequest = webServer.takeRequest();

        Assertions.assertEquals("GET", recordedRequest.getMethod());
        Assertions.assertEquals("/?name=john", recordedRequest.getPath());
    }

}
