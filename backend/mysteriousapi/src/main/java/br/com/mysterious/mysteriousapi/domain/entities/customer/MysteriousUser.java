package br.com.mysterious.mysteriousapi.domain.entities.customer;

import java.util.UUID;

public class MysteriousUser {
    private UUID mysteriousUserId;
    private String username;
    private String email;
    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
