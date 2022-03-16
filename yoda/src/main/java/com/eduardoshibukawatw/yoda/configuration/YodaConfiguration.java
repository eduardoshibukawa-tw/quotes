package com.eduardoshibukawatw.yoda.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "yoda")
@Data
public class YodaConfiguration {

    private List<String> quotes;
}
