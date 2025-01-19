package br.com.mysterious.mysteriousapi.presentation.dtos.response;

public record MysteriousUserSignInResponseDTO (
        String token,
        MysteriousUserResponseDTO mysteriousUser
){
}
