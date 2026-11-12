package org.grow.smartleadqualification.qualifier;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@EnableConfigurationProperties(QualificatorWebClientPropertiesConfig.class)
@AllArgsConstructor
class QualifierWebClientConfig {
    private final QualificatorWebClientPropertiesConfig config;



    @Bean
    QualifierWebClient qualifierService() {
        WebClient webClient = WebClient.builder()
                .baseUrl(config.url)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + config.token)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(WebClientAdapter.create(webClient)).build();

        return httpServiceProxyFactory.createClient(QualifierWebClient.class);
    };
}
