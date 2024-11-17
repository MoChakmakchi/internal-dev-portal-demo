package com.aviva.uk.integration.mycontainer.config;

import com.aviva.uk.integration.common.security.client.mvc.BasicAuthMatcher;
import com.aviva.uk.integration.common.security.client.mvc.SecurityRepositoryMvcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    SecurityRepositoryMvcClient securityRepositoryMvcClient;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
        throws Exception {
        auth.authenticationProvider(securityRepositoryMvcClient);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().authorizeRequests()
            .requestMatchers(new BasicAuthMatcher()).hasRole("USER")
            .antMatchers("/actuator/**").permitAll()
            .anyRequest().denyAll()
            .and().httpBasic();
    }
}
