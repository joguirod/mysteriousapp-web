package br.com.mysterious.mysteriousapi.application.outputs;

import br.com.mysterious.mysteriousapi.domain.entities.customer.MysteriousUser;

public record MysteriousUserSignInOutput (
        MysteriousUser mysteriousUser,
        String token
){
}
