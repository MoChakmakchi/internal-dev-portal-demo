package com.aviva.uk.integration.myapi.controllers;

import com.aviva.uk.integration.myapi.services.ApiService;
import org.apache.cxf.message.MessageContentsList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;

@RestController
@Validated
@RequestMapping("/")
public class ApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiController.class);
    public static final String REQUESTING_SYSTEM = "Requesting-System";
    @Autowired
    private ApiService apiService;

    @GetMapping("{exampleId}/{exampleName}")
    public Object getData(@PathVariable("exampleId") String exampleId, @PathVariable("exampleName") String exampleName,
                          @RequestHeader(REQUESTING_SYSTEM) @NotBlank(message = "Requesting-System missing from header") String requestingSystem) {

        LOGGER.info("ApiController starting..");
        final MessageContentsList msgContentsList = new MessageContentsList();
        msgContentsList.add(0,exampleId);
        msgContentsList.add(1,exampleName);
        final Map<String, Object> headersMap = new HashMap<>();
        headersMap.put(REQUESTING_SYSTEM, requestingSystem);

        Object response = apiService.invokeService(headersMap,msgContentsList);

        LOGGER.info("End");

        return response;

    }
}
