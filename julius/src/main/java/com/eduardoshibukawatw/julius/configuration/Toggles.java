package com.eduardoshibukawatw.julius.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "toggle")
@Data
public class Toggles {

    private String paramQuotes;

    public boolean isParamQuotesEnabled() {
        return "true".equals(this.paramQuotes);
    }
}
