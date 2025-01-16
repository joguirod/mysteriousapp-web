package br.com.mysterious.mysteriousapi.presentation.dtos.mappers;

import br.com.mysterious.mysteriousapi.domain.entities.customer.MysteriousUser;
import br.com.mysterious.mysteriousapi.presentation.dtos.request.MysteriousUserRequestDTO;
import br.com.mysterious.mysteriousapi.presentation.dtos.response.MysteriousUserResponseDTO;

public class MysteriousUserDTOMapper {
    public MysteriousUser toMysteriousUser(MysteriousUserRequestDTO mysteriousUserRequestDTO) {
        MysteriousUser mysteriousUser = new MysteriousUser();
        mysteriousUser.setEmail(mysteriousUserRequestDTO.getEmail());
        mysteriousUser.setPassword(mysteriousUserRequestDTO.getPassword());
        mysteriousUser.setUsername(mysteriousUserRequestDTO.getUsername());

        return mysteriousUser;
    }

    public MysteriousUserResponseDTO toMysteriousUserResponseDTO(MysteriousUser mysteriousUser) {
        MysteriousUserResponseDTO responseDTO = new MysteriousUserResponseDTO();
        responseDTO.setMysteriousUserId(mysteriousUser.getMysteriousUserId());
        responseDTO.setEmail(mysteriousUser.getEmail());
        responseDTO.setUsername(mysteriousUser.getUsername());

        return responseDTO;
    }
}
