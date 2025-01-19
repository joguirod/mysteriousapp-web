package br.com.mysterious.mysteriousapi.common.config;

import br.com.mysterious.mysteriousapi.application.providers.HashProvider;
import br.com.mysterious.mysteriousapi.application.providers.TokenProvider;
import br.com.mysterious.mysteriousapi.infrastructure.providers.BCryptHashProvider;
import br.com.mysterious.mysteriousapi.infrastructure.security.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class ProvidersConfig {
    @Bean
    HashProvider hashProvider() {
        return new BCryptHashProvider(new BCryptPasswordEncoder());
    }

    @Bean
    TokenProvider tokenProvider() {
        return new JwtTokenProvider();
    }
}
