package com.eduardoshibukawatw.yoda.quotes;

import com.eduardoshibukawatw.yoda.configuration.YodaConfiguration;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SpringCloudQuote implements QuotesUseCase{

    private final YodaConfiguration configuration;

    public SpringCloudQuote(YodaConfiguration configuration) {
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
