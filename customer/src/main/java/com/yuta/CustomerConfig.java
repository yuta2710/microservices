package com.yuta;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CustomerConfig {
    @Bean
    @LoadBalanced // mean that the customer request can come to server 1 or server 2 loading balance
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
