package br.com.mysterious.mysteriousapi.common.config;

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
    SignupMysteriousUserUseCase signupMysteriousUserUseCase(MysteriousUserRepository mysteriousUserRepository) {
        return new SignupMysteriousUserUseCase(mysteriousUserRepository);
    }
}
