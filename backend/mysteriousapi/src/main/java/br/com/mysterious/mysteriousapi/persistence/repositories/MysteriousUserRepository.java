package br.com.mysterious.mysteriousapi.persistence.repositories;

import br.com.mysterious.mysteriousapi.domain.entities.customer.MysteriousUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MysteriousUserRepository extends JpaRepository<MysteriousUser, UUID> {
    Optional<MysteriousUser> findByEmail(String email);
}
