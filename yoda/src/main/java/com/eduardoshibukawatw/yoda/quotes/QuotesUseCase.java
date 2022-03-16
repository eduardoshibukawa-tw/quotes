package com.eduardoshibukawatw.yoda.quotes;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface QuotesUseCase {

    String quote();

    List<String> quotes();

    class DefaultQuote implements QuotesUseCase {

        @Override
        public String quote() {
            return "Default quote";
        }

        @Override
        public List<String> quotes() {
            return Stream.of("Default quote").collect(Collectors.toUnmodifiableList());
        }
    }
}
