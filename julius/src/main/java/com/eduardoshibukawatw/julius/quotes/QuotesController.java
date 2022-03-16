package com.eduardoshibukawatw.julius.quotes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuotesController {

    private final QuotesUseCase quotesUseCase;

    QuotesController(QuotesUseCase quotesUseCase) {
        this.quotesUseCase = quotesUseCase;
    }

    @GetMapping("quote")
    public String quote() {
        return quotesUseCase.quote();
    }

    @GetMapping("quotes")
    public List<String> quotes() {
        return quotesUseCase.quotes();
    }
}