package com.eduardoshibukawatw.yoda.quotes;

import com.eduardoshibukawatw.yoda.configuration.Toggles;
import com.eduardoshibukawatw.yoda.configuration.YodaConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RefreshScope
public class ToggledQuotes implements QuotesUseCase {

    private final Toggles toggles;
    private final YodaConfiguration saciConfiguration;
    private QuotesUseCase useCase;

    public ToggledQuotes(Toggles toggles, YodaConfiguration configuration) {
        this.toggles = toggles;
        this.saciConfiguration = configuration;
    }

    @PostConstruct
    private void init() {
        if (toggles.isParamQuotesEnabled()) {
            this.useCase = new SpringCloudQuote(saciConfiguration);
        } else {
            this.useCase = new DefaultQuote();
        }
    }

    @Override
    public String quote() {
        return this.useCase.quote();
    }
}

