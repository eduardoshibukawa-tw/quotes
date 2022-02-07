package com.eduardoshibukawatw.julius.quotes;

public interface QuotesUseCase {

    String quote();

    class DefaultQuote implements QuotesUseCase {

        @Override
        public String quote() {
            return "Default quote";
        }
    }
}
