package hello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
   ThirdPartyClient thirdPartyClient() {
        return new ThirdPartyClient();
    }
}
