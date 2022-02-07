package com.eduardoshibukawatw.yoda.quotes;

import com.eduardoshibukawatw.yoda.configuration.YodaConfiguration;

public class SpringCloudQuote implements QuotesUseCase{

    private final YodaConfiguration configuration;

    public SpringCloudQuote(YodaConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public String quote() {
        return configuration.getQuotes();
    }
}
