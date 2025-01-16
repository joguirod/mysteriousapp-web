package br.com.mysterious.mysteriousapi.presentation.dtos.request;

import java.util.UUID;

public class MysteriousUserRequestDTO {
    private String username;
    private String email;
    private String password;

    public MysteriousUserRequestDTO() {
    }

    public MysteriousUserRequestDTO(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
