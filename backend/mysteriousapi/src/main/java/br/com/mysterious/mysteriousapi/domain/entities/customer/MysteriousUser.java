package br.com.mysterious.mysteriousapi.domain.entities.customer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Entity
@EqualsAndHashCode
@ToString
public class MysteriousUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID mysteriousUserId;
    private String username;
    private String email;
    private String password;
    private MysteriousUserType mysteriousUserType;

    public MysteriousUser() {
    }

    public MysteriousUser(UUID mysteriousUserId, String username, String email, String password) {
        this.mysteriousUserId = mysteriousUserId;
        this.username = username;
        this.email = email;
        this.password = password;
    }

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

    public MysteriousUserType getMysteriousUserType() {
        return mysteriousUserType;
    }

    public void setMysteriousUserType(MysteriousUserType mysteriousUserType) {
        this.mysteriousUserType = mysteriousUserType;
    }
}
