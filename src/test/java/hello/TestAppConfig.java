package hello;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestAppConfig {
    @Bean
    ThirdPartyClient thirdPartyClient() {
        return new ThirdPartyClient() {
            @Override
            public String get() {
                return " mock!!!";
            }
        };
    }
}
