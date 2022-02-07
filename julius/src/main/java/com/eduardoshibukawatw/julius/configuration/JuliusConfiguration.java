package com.eduardoshibukawatw.yoda.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "julius")
@Data
public class YodaConfiguration {

    private String quotes;
}
