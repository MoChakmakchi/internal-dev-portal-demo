package com.aviva.uk.integration.myapi.controllers;

import com.aviva.uk.integration.myapi.Application;
import org.apache.camel.Exchange;
import org.apache.cxf.message.MessageContentsList;
import org.junit.jupiter.api.Test;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashMap;
import java.util.Map;
import static com.aviva.uk.integration.myapi.controllers.ApiController.REQUESTING_SYSTEM;
import static org.junit.jupiter.api.Assertions.assertEquals;

@CamelSpringBootTest
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = Application.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = Application.class)
class ApiControllerTest {

    @Autowired
    ApiController controller;

    @Test
    void test_Service_success() throws Exception {

        final MessageContentsList msgContentsList = new MessageContentsList();
        msgContentsList.add(0, "123");
        msgContentsList.add(1, "john");
        final Map<String, Object> headersMap = new HashMap<>();

        headersMap.put(REQUESTING_SYSTEM, "appian");
        Object obj = controller.getData("123", "john", "appian");
        ResponseEntity<Object> response = (ResponseEntity<Object>) obj;

        assertEquals(200, response.getStatusCode().value());
    }
}