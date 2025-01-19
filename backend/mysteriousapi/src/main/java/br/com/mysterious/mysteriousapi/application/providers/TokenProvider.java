package br.com.mysterious.mysteriousapi.application.providers;

import br.com.mysterious.mysteriousapi.domain.entities.customer.MysteriousUser;

public interface TokenProvider {
    public String generateToken(MysteriousUser user);
    public String validateToken(String token);
}
