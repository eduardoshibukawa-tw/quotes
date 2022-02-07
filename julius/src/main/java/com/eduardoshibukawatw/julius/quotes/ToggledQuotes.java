package com.eduardoshibukawatw.julius.quotes;

import com.eduardoshibukawatw.julius.configuration.Toggles;
import com.eduardoshibukawatw.julius.configuration.JuliusConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RefreshScope
public class ToggledQuotes implements QuotesUseCase {

    private final Toggles toggles;
    private final JuliusConfiguration configuration;
    private QuotesUseCase useCase;

    public ToggledQuotes(Toggles toggles, JuliusConfiguration configuration) {
        this.toggles = toggles;
        this.configuration = configuration;
    }

    @PostConstruct
    private void init() {
        if (toggles.isParamQuotesEnabled()) {
            this.useCase = new SpringCloudQuote(configuration);
        } else {
            this.useCase = new DefaultQuote();
        }
    }

    @Override
    public String quote() {
        return this.useCase.quote();
    }
}

