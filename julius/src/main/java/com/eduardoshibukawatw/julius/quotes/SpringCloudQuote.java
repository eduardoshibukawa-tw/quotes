package com.eduardoshibukawatw.julius.quotes;

import com.eduardoshibukawatw.julius.configuration.JuliusConfiguration;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SpringCloudQuote implements QuotesUseCase {

    private final JuliusConfiguration configuration;

    public SpringCloudQuote(JuliusConfiguration configuration) {
        this.configuration = configuration;
    }

    @SuppressWarnings("ComparatorMethodParameterNotUsed")
    @Override
    public String quote() {
        return configuration.getQuotes().stream()
                .sorted((o1, o2) -> ThreadLocalRandom.current().nextInt(-1, 2))
                .findAny().orElse("No quote found");
    }

    @Override
    public List<String> quotes() {
        return configuration.getQuotes();
    }
}
