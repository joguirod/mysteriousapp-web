package br.com.mysterious.mysteriousapi.presentation.controllers;

import br.com.mysterious.mysteriousapi.application.exceptions.InvalidCredentialsException;
import br.com.mysterious.mysteriousapi.application.exceptions.MysteriousUserAlreadyExistsException;
import br.com.mysterious.mysteriousapi.application.exceptions.MysteriousUserNotFoundException;
import br.com.mysterious.mysteriousapi.application.outputs.MysteriousUserSignInOutput;
import br.com.mysterious.mysteriousapi.application.usecases.mysteriousUser.GetMysteriousUserByIdUseCase;
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
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/mysteriousUser")
public class MysteriousUserController {
    private final SignInMysteriousUserUseCase signInMysteriousUserUseCase;
    private final GetMysteriousUserByIdUseCase getMysteriousUserByIdUseCase;
    private SignupMysteriousUserUseCase signupMysteriousUserUseCase;
    private MysteriousUserDTOMapper mysteriousUserDTOMapper;

    public MysteriousUserController(SignupMysteriousUserUseCase signupMysteriousUserUseCase, MysteriousUserDTOMapper mysteriousUserDTOMapper, SignInMysteriousUserUseCase signInMysteriousUserUseCase, GetMysteriousUserByIdUseCase getMysteriousUserByIdUseCase) {
        this.signupMysteriousUserUseCase = signupMysteriousUserUseCase;
        this.mysteriousUserDTOMapper = mysteriousUserDTOMapper;
        this.signInMysteriousUserUseCase = signInMysteriousUserUseCase;
        this.getMysteriousUserByIdUseCase = getMysteriousUserByIdUseCase;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MysteriousUserResponseDTO> getMysteriousUserById(@PathVariable("id") UUID id) throws MysteriousUserNotFoundException {
        MysteriousUser mysteriousUser = getMysteriousUserByIdUseCase.execute(id);
        return new ResponseEntity<>(mysteriousUserDTOMapper.toMysteriousUserResponseDTO(mysteriousUser), HttpStatus.OK);
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
