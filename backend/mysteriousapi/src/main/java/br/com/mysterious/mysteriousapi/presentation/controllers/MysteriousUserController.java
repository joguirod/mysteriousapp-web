package br.com.mysterious.mysteriousapi.presentation.controllers;

import br.com.mysterious.mysteriousapi.application.exceptions.MysteriousUserAlreadyExistsException;
import br.com.mysterious.mysteriousapi.application.usecases.mysteriousUser.SignupMysteriousUserUseCase;
import br.com.mysterious.mysteriousapi.domain.entities.customer.MysteriousUser;
import br.com.mysterious.mysteriousapi.presentation.dtos.mappers.MysteriousUserDTOMapper;
import br.com.mysterious.mysteriousapi.presentation.dtos.request.MysteriousUserRequestDTO;
import br.com.mysterious.mysteriousapi.presentation.dtos.response.MysteriousUserResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mysteriousUser")
public class MysteriousUserController {
    private SignupMysteriousUserUseCase signupMysteriousUserUseCase;
    private MysteriousUserDTOMapper mysteriousUserDTOMapper;

    public MysteriousUserController(SignupMysteriousUserUseCase signupMysteriousUserUseCase, MysteriousUserDTOMapper mysteriousUserDTOMapper) {
        this.signupMysteriousUserUseCase = signupMysteriousUserUseCase;
        this.mysteriousUserDTOMapper = mysteriousUserDTOMapper;
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<MysteriousUserResponseDTO> signUpMysteriousUser(@RequestBody MysteriousUserRequestDTO mysteriousUserRequestDTO) throws MysteriousUserAlreadyExistsException {
        MysteriousUser mysteriousUser = mysteriousUserDTOMapper.toMysteriousUser(mysteriousUserRequestDTO);
        mysteriousUser = signupMysteriousUserUseCase.execute(mysteriousUser);
        return new ResponseEntity<>(mysteriousUserDTOMapper.toMysteriousUserResponseDTO(mysteriousUser), HttpStatus.CREATED);
    }
}
