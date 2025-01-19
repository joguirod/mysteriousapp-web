package br.com.mysterious.mysteriousapi.application.usecases.mysteriousUser;

import br.com.mysterious.mysteriousapi.application.exceptions.InvalidCredentialsException;
import br.com.mysterious.mysteriousapi.application.outputs.MysteriousUserSignInOutput;
import br.com.mysterious.mysteriousapi.application.providers.HashProvider;
import br.com.mysterious.mysteriousapi.application.providers.TokenProvider;
import br.com.mysterious.mysteriousapi.domain.entities.customer.MysteriousUser;
import br.com.mysterious.mysteriousapi.persistence.repositories.MysteriousUserRepository;

import java.util.Optional;

public class SignInMysteriousUserUseCase {

    private final MysteriousUserRepository mysteriousUserRepository;
    private final HashProvider hashProvider;
    private final TokenProvider tokenProvider;

    public SignInMysteriousUserUseCase(MysteriousUserRepository mysteriousUserRepository, HashProvider hashProvider, TokenProvider tokenProvider) {
        this.mysteriousUserRepository = mysteriousUserRepository;
        this.hashProvider = hashProvider;
        this.tokenProvider = tokenProvider;
    }

    public MysteriousUserSignInOutput execute(MysteriousUser mysteriousUserAttempt) throws InvalidCredentialsException {
        Optional<MysteriousUser> mysteriousUserTemp = mysteriousUserRepository.findByEmail(mysteriousUserAttempt.getEmail());
        if (mysteriousUserTemp.isEmpty()) {
            throw new InvalidCredentialsException("Email and/or password invalid");
        }
        MysteriousUser mysteriousUser = mysteriousUserTemp.get();

        validateCredentials(mysteriousUserAttempt.getPassword(), mysteriousUser.getPassword());

        String token = tokenProvider.generateToken(mysteriousUser);

        return new MysteriousUserSignInOutput(mysteriousUser, token);
    }

    private void validateCredentials(String passwordReceived, String hash) throws InvalidCredentialsException {
        Boolean credentialsValid = hashProvider.verify(passwordReceived, hash);
        if (!credentialsValid) {
            throw new InvalidCredentialsException("Email or/and password invalid");
        }
    }
}
