package io.educative.integration;

import io.educative.services.TodoService;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TodoServiceMockWebServerTest {

    public static MockWebServer mockBackEnd;

    private TodoService todoService;

    @BeforeAll
    static void setUp() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    /*@BeforeEach
    void initialize() {
        String baseUrl = String.format("http://localhost:%s",
                mockBackEnd.getPort());
        todoService = new TodoService(baseUrl);
    }*/

    /*@Test
    void getEmployeeById() throws Exception {
        Employee mockEmployee = new Employee(100, "Adam", "Sandler",
                32, Role.LEAD_ENGINEER);
        mockBackEnd.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(mockEmployee))
                .addHeader("Content-Type", "application/json"));

        Mono<Employee> employeeMono = employeeService.getEmployeeById(100);

        StepVerifier.create(employeeMono)
                .expectNextMatches(employee -> employee.getRole()
                        .equals(Role.LEAD_ENGINEER))
                .verifyComplete();
    }*/


}
