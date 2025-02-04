package br.com.mysterious.mysteriousapi.common.config;

import br.com.mysterious.mysteriousapi.application.providers.HashProvider;
import br.com.mysterious.mysteriousapi.application.providers.TokenProvider;
import br.com.mysterious.mysteriousapi.application.usecases.mysteriousUser.GetMysteriousUserByIdUseCase;
import br.com.mysterious.mysteriousapi.application.usecases.mysteriousUser.SignInMysteriousUserUseCase;
import br.com.mysterious.mysteriousapi.application.usecases.mysteriousUser.SignupMysteriousUserUseCase;
import br.com.mysterious.mysteriousapi.persistence.repositories.MysteriousUserRepository;
import br.com.mysterious.mysteriousapi.presentation.dtos.mappers.MysteriousUserDTOMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MysteriousUserConfig {

    @Bean
    MysteriousUserDTOMapper mysteriousUserDTOMapper() {
        return new MysteriousUserDTOMapper();
    }

    @Bean
    SignupMysteriousUserUseCase signupMysteriousUserUseCase(MysteriousUserRepository mysteriousUserRepository, HashProvider hashProvider) {
        return new SignupMysteriousUserUseCase(mysteriousUserRepository, hashProvider);
    }

    @Bean
    SignInMysteriousUserUseCase signInMysteriousUserUseCase(MysteriousUserRepository mysteriousUserRepository, HashProvider hashProvider, TokenProvider tokenProvider) {
        return new SignInMysteriousUserUseCase(mysteriousUserRepository, hashProvider, tokenProvider);
    }

    @Bean
    GetMysteriousUserByIdUseCase getMysteriousUserByIdUseCase(MysteriousUserRepository mysteriousUserRepository) {
        return new GetMysteriousUserByIdUseCase(mysteriousUserRepository);
    }
}
