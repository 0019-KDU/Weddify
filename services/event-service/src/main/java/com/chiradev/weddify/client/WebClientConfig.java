package com.chiradev.weddify.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient vendorWebClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8070/api/vendors") // Replace with Vendor Service URL
                .build();
    }
}
