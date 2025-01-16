package br.com.mysterious.mysteriousapi.presentation.dtos.response;

import java.util.UUID;

public class MysteriousUserResponseDTO {
    private UUID mysteriousUserId;
    private String username;
    private String email;

    public UUID getMysteriousUserId() {
        return mysteriousUserId;
    }

    public void setMysteriousUserId(UUID mysteriousUserId) {
        this.mysteriousUserId = mysteriousUserId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
