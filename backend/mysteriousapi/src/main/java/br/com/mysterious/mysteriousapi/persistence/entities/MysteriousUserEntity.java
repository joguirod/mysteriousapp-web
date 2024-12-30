package br.com.mysterious.mysteriousapi.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class MysteriousUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID mysteriousUserId;
    private String username;
    private String email;
    private String password;
}
