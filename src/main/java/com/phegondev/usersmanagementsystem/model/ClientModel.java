package com.phegondev.usersmanagementsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Long numeroMatricule;
    private Long numeroTelephone;
    private String adresse;
    private String nom;
    private String email;
    private String genre;
    private int age;
    @OneToMany(mappedBy = "client")
    private List<ReservationModel> reservations;
}
