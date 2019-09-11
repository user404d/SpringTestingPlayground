package vinkenstein;

import clients.ListingsClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@Configuration
@Profile("default")
public class AppConfig {
    @Bean
   ThirdPartyClient thirdPartyClient() {
        return new ThirdPartyClient();
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    ListingsClient listingsClient(RestTemplate restTemplate) {
       return new ListingsClient("http://localhost:8091", restTemplate);
    }
}
