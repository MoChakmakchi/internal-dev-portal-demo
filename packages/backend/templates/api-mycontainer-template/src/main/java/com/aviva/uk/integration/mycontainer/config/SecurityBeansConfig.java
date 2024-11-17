package com.aviva.uk.integration.mycontainer.config;

import com.aviva.uk.integration.common.security.client.mvc.SecurityRepositoryMvcClient;
import com.aviva.uk.integration.common.security.core.cache.GuavaLimitedAgeUserCacheAdapter;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Duration;


@Configuration
public class SecurityBeansConfig {

    @Value("${aviva.integration.securityrepository.uri}")
    private String securityrepositoryUri;

    @Value("${aviva.integration.securityrepository.keepAlive:15000}")
    private String keepAlive;

    @Value("${aviva.integration.securityrepository.connectionTimeout:15000}")
    private String connectionTimeout;

    @Value("${aviva.integration.securityrepository.isHttpCompressionEnabled:false}")
    private boolean isHttpCompressionEnabled;

    @Value("${aviva.integration.securityrepository.isRetryOnConnectionFailure:false}")
    private boolean isRetryOnConnectionFailure;

    @Value("${aviva.integration.securityrepository.nonceHashingAlgorithm:SHA-512}")
    private String nonceHashingAlgorithm;

    @Value("${aviva.integration.securityrepository.timeToLive:15000}")
    private long timeToLive;

    @Value("${aviva.integration.securityrepository.enableNonceCheck:true}")
    private boolean enableNonceCheck;

    @Value("${server.port:8080}")
    private String serverPort;

    @Value("${server.servlet.context-path:/api}")
    private String servletPath;

    @Bean(name = "GuavaLimitedAgeUserCacheAdapter")
    public GuavaLimitedAgeUserCacheAdapter createGuavaLimitedAgeUserCacheAdapter(){
        @NonNull Duration timeToLiveDuration = Duration.ofMillis(timeToLive);
        return new GuavaLimitedAgeUserCacheAdapter(timeToLiveDuration);}

    @Bean(name = "securityRepositoryMvcClient")
    protected SecurityRepositoryMvcClient securityRepositoryClient() throws UnknownHostException {

        String contextPath = "http://"+InetAddress.getLocalHost().getHostName() + serverPort + servletPath;

        return new SecurityRepositoryMvcClient(
            contextPath,
            securityrepositoryUri,
            this.restTemplate(),
            this.createGuavaLimitedAgeUserCacheAdapter(),
            nonceHashingAlgorithm,
            enableNonceCheck
        );
    }

    @NonNull
    protected RestTemplate restTemplate() {
        SocketConfig sockConfig = SocketConfig.custom().setSoKeepAlive(true).setSoTimeout(Integer.parseInt(
            connectionTimeout)).build();
        PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
        manager.setDefaultSocketConfig(sockConfig);
        HttpClientBuilder clientBuilder = HttpClientBuilder.create()
                                                           .disableCookieManagement()
                                                           .disableRedirectHandling()
                                                           .disableAuthCaching()
                                                           .setKeepAliveStrategy((response, context) -> Long.parseLong(
                                                               keepAlive))
                                                           .setConnectionManager(manager);
        if (!isHttpCompressionEnabled) {
            clientBuilder.disableContentCompression();
        }

        if (!isRetryOnConnectionFailure) {
            clientBuilder.disableAutomaticRetries();
        }

        return new RestTemplate(new HttpComponentsClientHttpRequestFactory(clientBuilder.build()));
    }
}
