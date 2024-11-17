package com.aviva.uk.integration.myapi.mapper;

import org.apache.camel.Exchange;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

@CamelSpringBootTest
class DataMapperTest {

    @Mock
    Exchange exchange;

    @Mock
    DataMapper dataMapper;

    @Test
    void testMapRequest() {
        dataMapper.mapRequest(exchange);
        verify(dataMapper, times(1)).mapRequest(exchange);
    }

    @Test
    void testMapResponse() {
        dataMapper.mapResponse(exchange);
        verify(dataMapper, times(1)).mapResponse(exchange);
    }
}