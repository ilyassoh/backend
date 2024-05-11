package com.phegondev.usersmanagementsystem.model;

import com.phegondev.usersmanagementsystem.model.enumm.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParkingModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nom;
    private String emplacement;
    private int capaciteTotale;
    private int placesDisponibles;
    @CreationTimestamp
    private LocalDateTime date_ouverture;
    @UpdateTimestamp
    private LocalDateTime date_fermeture;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToMany(mappedBy = "parking", cascade = CascadeType.ALL)
    private List<ReservationModel> reservations;
}
