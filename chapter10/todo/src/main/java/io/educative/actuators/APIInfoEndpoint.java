package io.educative.actuators;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "apiInfo")
public class APIInfoEndpoint {

    @ReadOperation
    public String apiInfo() {
        String apiInfo = "The todo app expose two APIs - Todo and TodoType.";
        return apiInfo;
    }

}
