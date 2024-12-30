package br.com.mysterious.mysteriousapi.persistence.entities;

import br.com.mysterious.mysteriousapi.domain.entities.customer.MysteriousUser;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class MysteriousCustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID mysteriousCustomerId;
    private String name;
    private String phone;
    @OneToOne
    private MysteriousUserEntity mysteriousUser;
}
