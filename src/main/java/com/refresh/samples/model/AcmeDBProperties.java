package com.refresh.samples.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix="acme.db")
@Component
public class AcmeDBProperties {

        private String driverClassName;
        private String dbname;
        private String url;
        private String username;
        private String password;

}
