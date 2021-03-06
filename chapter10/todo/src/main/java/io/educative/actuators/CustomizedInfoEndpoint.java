package io.educative.actuators;

import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.WebEndpointResponse;
import org.springframework.boot.actuate.endpoint.web.annotation.EndpointWebExtension;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@EndpointWebExtension(endpoint = InfoEndpoint.class)
public class CustomizedInfoEndpoint {

    private InfoEndpoint infoEndpoint;

    public CustomizedInfoEndpoint(InfoEndpoint infoEndpoint) {
        this.infoEndpoint = infoEndpoint;
    }

    @ReadOperation
    public WebEndpointResponse<Map> info() {
        Map<String, Object> info = this.infoEndpoint.info();
        String apiInfo = new APIInfoEndpoint().apiInfo();

        //copying unmodifiableMap into the local map
        HashMap<String, Object> modifiableInfo = new HashMap<>();
        info.keySet().forEach(stringObjectEntry -> modifiableInfo.put(stringObjectEntry, info.get(stringObjectEntry)));

        modifiableInfo.put("apiInfo", apiInfo);
        return new WebEndpointResponse<>(modifiableInfo);
    }

}