package io.ibex.bookservice;

import io.ibex.bookservice.utils.RestTemplateLoggingInterceptor;
import java.util.Collections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@EnableEurekaClient
@SpringBootApplication
public class BookServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookServiceApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        final ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

        RestTemplate restTemplate = new RestTemplate(requestFactory);
        restTemplate.setInterceptors(Collections.singletonList(new RestTemplateLoggingInterceptor()));
        restTemplate.setRequestFactory( new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));

        return restTemplate;
    }
}

