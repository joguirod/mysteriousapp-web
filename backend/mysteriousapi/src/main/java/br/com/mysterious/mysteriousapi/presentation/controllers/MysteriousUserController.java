package br.com.mysterious.mysteriousapi.presentation.controllers;

import br.com.mysterious.mysteriousapi.application.exceptions.InvalidCredentialsException;
import br.com.mysterious.mysteriousapi.application.exceptions.MysteriousUserAlreadyExistsException;
import br.com.mysterious.mysteriousapi.application.outputs.MysteriousUserSignInOutput;
import br.com.mysterious.mysteriousapi.application.usecases.mysteriousUser.SignInMysteriousUserUseCase;
import br.com.mysterious.mysteriousapi.application.usecases.mysteriousUser.SignupMysteriousUserUseCase;
import br.com.mysterious.mysteriousapi.domain.entities.customer.MysteriousUser;
import br.com.mysterious.mysteriousapi.presentation.dtos.mappers.MysteriousUserDTOMapper;
import br.com.mysterious.mysteriousapi.presentation.dtos.request.MysteriousUserRequestDTO;
import br.com.mysterious.mysteriousapi.presentation.dtos.request.MysteriousUserSignInRequestDTO;
import br.com.mysterious.mysteriousapi.presentation.dtos.response.MysteriousUserResponseDTO;
import br.com.mysterious.mysteriousapi.presentation.dtos.response.MysteriousUserSignInResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mysteriousUser")
public class MysteriousUserController {
    private final SignInMysteriousUserUseCase signInMysteriousUserUseCase;
    private SignupMysteriousUserUseCase signupMysteriousUserUseCase;
    private MysteriousUserDTOMapper mysteriousUserDTOMapper;

    public MysteriousUserController(SignupMysteriousUserUseCase signupMysteriousUserUseCase, MysteriousUserDTOMapper mysteriousUserDTOMapper, SignInMysteriousUserUseCase signInMysteriousUserUseCase) {
        this.signupMysteriousUserUseCase = signupMysteriousUserUseCase;
        this.mysteriousUserDTOMapper = mysteriousUserDTOMapper;
        this.signInMysteriousUserUseCase = signInMysteriousUserUseCase;
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<MysteriousUserResponseDTO> signUpMysteriousUser(@RequestBody MysteriousUserRequestDTO mysteriousUserRequestDTO) throws MysteriousUserAlreadyExistsException {
        MysteriousUser mysteriousUser = mysteriousUserDTOMapper.toMysteriousUser(mysteriousUserRequestDTO);
        mysteriousUser = signupMysteriousUserUseCase.execute(mysteriousUser);
        return new ResponseEntity<>(mysteriousUserDTOMapper.toMysteriousUserResponseDTO(mysteriousUser), HttpStatus.CREATED);
    }

    @PostMapping("/auth/signin")
    public ResponseEntity<MysteriousUserSignInResponseDTO> signMysteriousUser(@RequestBody MysteriousUserSignInRequestDTO signInRequestDTO) throws InvalidCredentialsException {
        MysteriousUser mysteriousUser = mysteriousUserDTOMapper.toMysteriousUser(signInRequestDTO);
        MysteriousUserSignInOutput signInResponseDTO = signInMysteriousUserUseCase.execute(mysteriousUser);
        return new ResponseEntity<>(mysteriousUserDTOMapper.toSignInReponseDTO(signInResponseDTO), HttpStatus.OK);
    }
}
