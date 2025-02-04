package br.com.mysterious.mysteriousapi.domain.entities.customer;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "mysterioususertype")
public class MysteriousUserType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mysterious_user_type")
    private UUID mysteriousUserTypeId;
    @Column(name = "descricao")
    private String description;

    public MysteriousUserType() {
    }

    public UUID getMysteriousUserTypeId() {
        return mysteriousUserTypeId;
    }

    public void setMysteriousUserTypeId(UUID mysteriousUserTypeId) {
        this.mysteriousUserTypeId = mysteriousUserTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
