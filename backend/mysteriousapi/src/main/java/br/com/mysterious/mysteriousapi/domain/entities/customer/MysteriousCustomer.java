package br.com.mysterious.mysteriousapi.domain.entities.customer;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@EqualsAndHashCode
@ToString
public class MysteriousCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID mysteriousCustomerId;
    private String name;
    private String phone;
    @OneToOne
    private MysteriousUser mysteriousUser;

    public MysteriousCustomer() {
    }

    public MysteriousCustomer(UUID mysteriousCustomerId) {
        this.mysteriousCustomerId = mysteriousCustomerId;
    }

    public MysteriousCustomer(UUID mysteriousCustomerId, String name, String phone, MysteriousUser mysteriousUser) {
        this.mysteriousCustomerId = mysteriousCustomerId;
        this.name = name;
        this.phone = phone;
        this.mysteriousUser = mysteriousUser;
    }

    public UUID getMysteriousCustomerId() {
        return mysteriousCustomerId;
    }

    public void setMysteriousCustomerId(UUID mysteriousCustomerId) {
        this.mysteriousCustomerId = mysteriousCustomerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public MysteriousUser getMysteriousUser() {
        return mysteriousUser;
    }

    public void setMysteriousUser(MysteriousUser mysteriousUser) {
        this.mysteriousUser = mysteriousUser;
    }
}
