package vinkenstein;

import clients.ListingsClient;
import domain.PricingEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Bean String remoteServerHostname() {
       return "localhost"; //"A-TD1EMGTFM";
    } ;

    @Bean
    ThirdPartyClient thirdPartyClient() {
        return new ThirdPartyClient();
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    ListingsClient listingsClient(RestTemplate restTemplate, String remoteServerHostname) {
        return new ListingsClient("http://"+remoteServerHostname+":8091", restTemplate);
    }

    @Bean
    PricingEngine pricingEngine(RestTemplate restTemplate, String remoteServerHostname) {
        return new PricingEngine(restTemplate, remoteServerHostname);
    }
}
