package br.com.mysterious.mysteriousapi.application.usecases.mysteriousUser;

import br.com.mysterious.mysteriousapi.application.exceptions.MysteriousUserAlreadyExistsException;
import br.com.mysterious.mysteriousapi.application.providers.HashProvider;
import br.com.mysterious.mysteriousapi.domain.entities.customer.MysteriousUser;
import br.com.mysterious.mysteriousapi.domain.entities.customer.MysteriousUserType;
import br.com.mysterious.mysteriousapi.persistence.repositories.MysteriousUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SignupMysteriousUserUseCase {
    private final MysteriousUserRepository mysteriousUserRepository;
    private final HashProvider hashProvider;

    public SignupMysteriousUserUseCase(MysteriousUserRepository mysteriousUserRepository, HashProvider hashProvider) {
        this.mysteriousUserRepository = mysteriousUserRepository;
        this.hashProvider = hashProvider;
    }

    public MysteriousUser execute(MysteriousUser mysteriousUser) throws MysteriousUserAlreadyExistsException {
        if (mysteriousUserRepository.findByEmail(mysteriousUser.getEmail()).isPresent()) {
            throw new MysteriousUserAlreadyExistsException("An user with the given email already exists");
        }

        mysteriousUser.setPassword(hashProvider.hash(mysteriousUser.getPassword()));

        // o cadastro é somente de usuários, o administrador é cadastro pelo banco
        mysteriousUser.setMysteriousUserType(MysteriousUserType.CUSTOMER);

        return mysteriousUserRepository.save(mysteriousUser);
    }
}
