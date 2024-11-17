package com.aviva.uk.integration.mycontainer.config;

import com.aviva.uk.integration.common.camel.framework.cxf.rest.ExtendedJacksonJsonMessageWriter;
import com.aviva.uk.integration.common.camel.framework.exception.ProblemDetailsExceptionMapper;
import com.aviva.uk.integration.common.logging.logger.EventLogger;
import com.aviva.uk.integration.common.logging.mdc.ServiceDataMDC;
import org.apache.cxf.transport.common.gzip.GZIPInInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*
Use this class to define beans required for project
 */
@Configuration
public class BeansConfig {

    @Bean(name = "eventLogger")
    public EventLogger getEventLogger() {
        return new EventLogger();
    }
    @Bean(name = "serviceDataMDC")
    public ServiceDataMDC getServiceDataMDC() {
        return new ServiceDataMDC();
    }

    @Bean(name = "exceptionMapper")
    public ProblemDetailsExceptionMapper getProblemDetailsExceptionMapper() { return new ProblemDetailsExceptionMapper(); }

    @Bean(name = "gzipInInterceptor")
    public GZIPInInterceptor getGZIPInInterceptor() {
        return new GZIPInInterceptor();
    }

    @Bean(name = "jacksonJsonProvider")
    public ExtendedJacksonJsonMessageWriter getJacksonJsonProvider() {
        return new ExtendedJacksonJsonMessageWriter();
    }
}
