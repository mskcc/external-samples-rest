package org.mskcc.igo.pi.external.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${external.sample.rest.admin.username}")
    private String adminUsername;
    @Value("${external.sample.rest.admin.password}")
    private String adminPassword;
    @Value("${external.sample.rest.user.username}")
    private String readonlyUsername;
    @Value("${external.sample.rest.user.password}")
    private String readonlyPassword;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser(readonlyUsername).password(readonlyPassword).roles("USER").and()
                .withUser(adminUsername).password(adminPassword).roles("ADMIN", "USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // As this service is not used through the crowser CSRF may be disabled. In case it would be accessed from
        // the browser it needs to be left enabled (in spring security by default)
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/**")
                .authenticated()
                .and().httpBasic().and();
    }
}
