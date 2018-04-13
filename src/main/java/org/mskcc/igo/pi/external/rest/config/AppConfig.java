package org.mskcc.igo.pi.external.rest.config;

import org.mskcc.igo.pi.external.jpa.gateway.JPAExternalSampleGateway;
import org.mskcc.igo.pi.external.rest.sample.ExternalSampleGateway;
import org.mskcc.igo.pi.external.rest.sample.ExternalSampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
public class AppConfig {
    @Autowired
    private ExternalSampleRepository externalSampleRepository;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public ExternalSampleGateway externalSampleGateway() {
        return new JPAExternalSampleGateway(externalSampleRepository);
    }

    @Configuration
    @Profile("prod")
    @PropertySource({
            "file:connection.properties",
            "file:connection-external.properties"
    })
    static class ProdPropertyConfig {

    }

    @Configuration
    @Profile("dev")
    @PropertySource({
            "file:connection-dev.properties",
            "file:connection-external-dev.properties"
    })
    static class DevPropertyConfig {
    }
}
