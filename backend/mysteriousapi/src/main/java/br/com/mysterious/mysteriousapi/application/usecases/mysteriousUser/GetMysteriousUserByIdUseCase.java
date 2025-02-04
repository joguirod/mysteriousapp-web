package br.com.mysterious.mysteriousapi.application.usecases.mysteriousUser;

import br.com.mysterious.mysteriousapi.application.exceptions.MysteriousUserNotFoundException;
import br.com.mysterious.mysteriousapi.domain.entities.customer.MysteriousUser;
import br.com.mysterious.mysteriousapi.persistence.repositories.MysteriousUserRepository;

import java.util.Optional;
import java.util.UUID;

public class GetMysteriousUserByIdUseCase {

    private final MysteriousUserRepository mysteriousUserRepository;

    public GetMysteriousUserByIdUseCase(MysteriousUserRepository mysteriousUserRepository) {
        this.mysteriousUserRepository = mysteriousUserRepository;
    }

    public MysteriousUser execute(UUID id) throws MysteriousUserNotFoundException {
        Optional<MysteriousUser> mysteriousUser = mysteriousUserRepository.findById(id);
        if (mysteriousUser.isEmpty()) {
            throw new MysteriousUserNotFoundException("User with the given ID not found");
        }

        return mysteriousUser.get();
    }
}
