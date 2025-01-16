package br.com.mysterious.mysteriousapi.application.usecases.mysteriousUser;

import br.com.mysterious.mysteriousapi.application.exceptions.MysteriousUserAlreadyExistsException;
import br.com.mysterious.mysteriousapi.domain.entities.customer.MysteriousUser;
import br.com.mysterious.mysteriousapi.domain.entities.customer.MysteriousUserType;
import br.com.mysterious.mysteriousapi.persistence.repositories.MysteriousUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SignupMysteriousUserUseCase {
    private final MysteriousUserRepository mysteriousUserRepository;
    private PasswordEncoder passwordEncoder;

    public SignupMysteriousUserUseCase(MysteriousUserRepository mysteriousUserRepository) {
        this.mysteriousUserRepository = mysteriousUserRepository;
    }

    public MysteriousUser execute(MysteriousUser mysteriousUser) throws MysteriousUserAlreadyExistsException {
        if (mysteriousUserRepository.findByEmail(mysteriousUser.getEmail()) == null) {
            throw new MysteriousUserAlreadyExistsException("An user with the given email already exists");
        }

        mysteriousUser.setPassword(passwordEncoder.encode(mysteriousUser.getPassword()));

        // o cadastro é somente de usuários, o administrador é cadastro pelo banco
        mysteriousUser.setMysteriousUserType(MysteriousUserType.CUSTOMER);

        return mysteriousUserRepository.save(mysteriousUser);
    }
}
