package com.aviva.uk.integration.myapi.mapper;

import com.aviva.uk.integration.myapi.models.Example;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.cxf.message.MessageContentsList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class DataMapper {

    @Autowired
    public Example example;

    public void mapRequest(Exchange exchange) {

        log.debug("mapRequest starts >>");

        // TODO Add request mapping logic here
        final MessageContentsList msgContentsList = exchange.getIn().getBody(MessageContentsList.class);
        if (null != msgContentsList) {
            String exampleId = (String) msgContentsList.get(0);
            String exampleName = (String) msgContentsList.get(1);
            exchange.setProperty("exampleId", exampleId);
            exchange.setProperty("exampleName", exampleName);
            log.debug("mapRequest ends >>");
        }
    }

    public void mapResponse(Exchange exchange) {

        log.debug("mapResponse starts >>");
        // TODO Add response mapping logic here
        String exampleId =  (String) exchange.getProperty("exampleId");
        String exampleName = (String) exchange.getProperty("exampleName");
        example.setId(exampleId);
        example.setName(exampleName);
        log.debug("mapResponse ends >>");
    }

}