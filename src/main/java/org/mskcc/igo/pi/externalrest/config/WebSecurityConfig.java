package org.mskcc.igo.pi.externalrest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${external.sample.rest.username}")
    private String externalSampleRestUsername;

    @Value("${external.sample.rest.password}")
    private String externalSampleRestPassword;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser(externalSampleRestUsername).password(externalSampleRestPassword).roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // As this service is not used through the crowser CSRF may be disabled. In case it would be accessed from
        // the browser it needs to be left enabled (in spring security by default)
        http.csrf().disable();
    }
}
