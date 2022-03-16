package com.eduardoshibukawatw.julius.quotes;

import com.eduardoshibukawatw.julius.configuration.JuliusConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SpringCloudQuoteTest {

    @Mock
    private JuliusConfiguration juliusConfiguration;

    @InjectMocks
    private SpringCloudQuote springCloudQuote;

    private final List<String> quotes = Stream.of("quote").collect(Collectors.toUnmodifiableList());

    @BeforeEach
    void setUp() {
        Mockito.when(juliusConfiguration.getQuotes()).thenReturn(quotes);
    }

    @Test
    void shouldGetQuote() {
        assertEquals(quotes.get(0), springCloudQuote.quote());
    }

    @Test
    void shouldGetQuotes() {
        assertEquals(quotes, springCloudQuote.quotes());
    }
}