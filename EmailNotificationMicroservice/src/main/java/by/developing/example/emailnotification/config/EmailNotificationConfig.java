package by.developing.example.emailnotification.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class EmailNotificationConfig {

    @Bean
    RestTemplate getResttemplate() {
        return new RestTemplate();
    }
}
