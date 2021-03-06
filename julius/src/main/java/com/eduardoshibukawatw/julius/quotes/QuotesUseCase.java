package com.eduardoshibukawatw.julius.quotes;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface QuotesUseCase {

    String quote();

    List<String> quotes();

    class DefaultQuote implements QuotesUseCase {

        @Override
        public String quote() {
            return "Disabled feature toggle quote julius service";
        }

        @Override
        public List<String> quotes() {
            return Stream.of("Disabled feature toggle quote julius service").collect(Collectors.toUnmodifiableList());
        }
    }
}
