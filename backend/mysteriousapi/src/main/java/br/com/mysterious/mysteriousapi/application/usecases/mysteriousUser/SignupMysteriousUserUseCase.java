package br.com.mysterious.mysteriousapi.application.usecases.mysteriousUser;

import br.com.mysterious.mysteriousapi.application.exceptions.MysteriousUserAlreadyExistsException;
import br.com.mysterious.mysteriousapi.application.mappers.MysteriousUserMapper;
import br.com.mysterious.mysteriousapi.domain.entities.customer.MysteriousUser;
import br.com.mysterious.mysteriousapi.persistence.entities.MysteriousUserEntity;
import br.com.mysterious.mysteriousapi.persistence.repositories.MysteriousUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SignupMysteriousUserUseCase {
    private final MysteriousUserRepository mysteriousUserRepository;
    private final MysteriousUserMapper mysteriousUserMapper;
    private PasswordEncoder passwordEncoder;

    public SignupMysteriousUserUseCase(MysteriousUserRepository mysteriousUserRepository, MysteriousUserMapper mysteriousUserMapper) {
        this.mysteriousUserRepository = mysteriousUserRepository;
        this.mysteriousUserMapper = mysteriousUserMapper;
    }

    public MysteriousUser execute(MysteriousUser mysteriousUser) throws MysteriousUserAlreadyExistsException {
        if (mysteriousUserRepository.findByEmail(mysteriousUser.getEmail()) == null) {
            throw new MysteriousUserAlreadyExistsException("An user with the given email already exists");
        }

        MysteriousUserEntity mysteriousUserEntity = mysteriousUserMapper.toEntity(mysteriousUser);
        mysteriousUserEntity.setPassword(passwordEncoder.encode(mysteriousUser.getPassword()));

        mysteriousUserEntity = mysteriousUserRepository.save(mysteriousUserEntity);
        return mysteriousUserMapper.toDomainObject(mysteriousUserEntity);
    }
}
