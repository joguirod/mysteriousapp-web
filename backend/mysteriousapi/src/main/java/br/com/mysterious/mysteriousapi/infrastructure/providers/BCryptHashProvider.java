package br.com.mysterious.mysteriousapi.infrastructure.providers;

import br.com.mysterious.mysteriousapi.application.providers.HashProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptHashProvider implements HashProvider {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public BCryptHashProvider(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public String hash(String input) {
        return bCryptPasswordEncoder.encode(input);
    }

    @Override
    public Boolean verify(String input, String hash) {
        return bCryptPasswordEncoder.matches(input, hash);
    }
}
