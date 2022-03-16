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
            return "Disabled feature toggle quote yoda service";
        }

        @Override
        public List<String> quotes() {
            return Stream.of("Disabled feature toggle quote yoda service").collect(Collectors.toUnmodifiableList());
        }
    }
}
