package io.ibex.bookservice.configs;


import io.ibex.bookservice.utils.RestTemplateLoggingInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class OAuthSecurityConfig {
    @Value("${o-auth.accessTokenUri}")
    private String accessTokenUri;
    @Value("${o-auth.clientId}")
    private String clientId;
    @Value("${o-auth.clientSecret}")
    private String clientSecret;
    @Value("${o-auth.userId}")
    private String userId;

    @Bean
    public ClientCredentialsResourceDetails clientCredentialResources() {
        ClientCredentialsResourceDetails resource = new ClientCredentialsResourceDetails();
        resource.setAccessTokenUri(accessTokenUri);
        resource.setClientId(clientId);
        resource.setId(userId);
        resource.setClientSecret(clientSecret);
        return resource;
    }

    @Bean
    public OAuth2RestOperations applicationApiTemplate() {
        AccessTokenRequest atr = new DefaultAccessTokenRequest();

        OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(clientCredentialResources(),
                new DefaultOAuth2ClientContext(atr));

        oAuth2RestTemplate.setInterceptors(Collections.singletonList(new RestTemplateLoggingInterceptor()));
        oAuth2RestTemplate.setRequestFactory( new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));

        return oAuth2RestTemplate;
    }
}
