package com.aviva.uk.integration.myapi.services;

import com.aviva.uk.integration.myapi.models.Example;
import com.aviva.uk.integration.common.errorhandling.ProblemDetails;
import com.aviva.uk.integration.common.errorhandling.ProblemDetailsException;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.cxf.message.MessageContentsList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
@Slf4j
@Service
public class ApiService {

    public static final String INTERNAL_PROCESSING_ERROR = "Internal Processing Error";

    private ProducerTemplate producerTemplate;

    public ApiService(@Autowired ProducerTemplate producerTemplate) {
        this.producerTemplate = producerTemplate;
    }

    public Object invokeService(Map<String, Object> headersMap, MessageContentsList msgContentsList) {
        Exchange responseExchange = producerTemplate.request("direct:searchUserRoute", exchange -> {
            exchange.getIn().setBody(msgContentsList);
            exchange.getIn().setHeaders(headersMap);

        });
        //This Exception Handler is common for All APi's
        Exception exception = (Exception) responseExchange.getProperty(Exchange.EXCEPTION_CAUGHT);

        if (null != exception) {
            if (exception instanceof ProblemDetailsException) {
                ProblemDetailsException detailsException = (ProblemDetailsException) exception;
                log.debug("End with Error");
                return new ResponseEntity<>(detailsException.getProblemDetails(),
                        HttpStatus.valueOf(detailsException.getProblemDetails().getStatus()));
            }
            else {
                log.debug("End with Error");
                ProblemDetails error = new ProblemDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        exception.getMessage(), INTERNAL_PROCESSING_ERROR);
                return new ResponseEntity<>(error, HttpStatus.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
            }
        }

        Example response = responseExchange.getIn().getBody(Example.class);

        log.debug("End");
        return ResponseEntity.ok().body(response);
    }
}
