package br.com.mysterious.mysteriousapi.persistence.entities;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class PickUpLocationEntity {
    private String adress;
    private String number;
    private String complement;
    private String city;
    private String state;
    // cep
    private String zip;
}
