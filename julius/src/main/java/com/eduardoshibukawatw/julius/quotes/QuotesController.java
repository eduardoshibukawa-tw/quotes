package com.eduardoshibukawatw.julius.quotes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuotesController {

    private final QuotesUseCase quotesUseCase;

    QuotesController(QuotesUseCase quotesUseCase) {
        this.quotesUseCase = quotesUseCase;
    }

    @GetMapping
    public String greeting() {
        return quotesUseCase.quote();
    }
}