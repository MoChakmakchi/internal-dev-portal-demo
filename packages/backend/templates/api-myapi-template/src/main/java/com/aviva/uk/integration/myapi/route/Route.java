package com.aviva.uk.integration.myapi.route;


import com.aviva.uk.integration.myapi.mapper.DataMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Route extends RouteBuilder {

    @Autowired
    private DataMapper dataMapper;

    @Override
    public void configure() throws Exception {
    log.debug("Configure entered for searchUserRoute");
        //@formatter:off
        from("direct:searchUserRoute")
                .routeId("searchUserRoute")
                .bean(dataMapper, "mapRequest")
                // TODO Add additional logic here, Replace the example endpoint with your actual endpoint
                //.to uri="cxfrs:bean:exampleEndpoint" id="exampleEndpointId"
                .bean(dataMapper,"mapResponse")
                .end();
        log.debug("Configure exited for searchUserRoute");

    }
}
