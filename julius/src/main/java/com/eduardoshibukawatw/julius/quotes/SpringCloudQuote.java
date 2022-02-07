package com.eduardoshibukawatw.julius.quotes;

import com.eduardoshibukawatw.julius.configuration.JuliusConfiguration;

public class SpringCloudQuote implements QuotesUseCase {

    private final JuliusConfiguration configuration;

    public SpringCloudQuote(JuliusConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public String quote() {
        return configuration.getQuotes();
    }
}
