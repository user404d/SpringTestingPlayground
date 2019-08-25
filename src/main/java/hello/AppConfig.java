package hello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("default")
public class AppConfig {
    @Bean
   ThirdPartyClient thirdPartyClient() {
        return new ThirdPartyClient();
    }
}
