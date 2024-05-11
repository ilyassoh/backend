package com.phegondev.usersmanagementsystem.model;


import com.phegondev.usersmanagementsystem.model.enumm.Status_Reservation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @CreationTimestamp
    private LocalDateTime date_entree;
    @UpdateTimestamp
    private LocalDateTime date_sortie;
    @Enumerated(EnumType.STRING)
    private Status_Reservation status;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private PlaceModel place;

    @ManyToOne
    @JoinColumn(name = "parking_id")
    private ParkingModel parking;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientModel client;
}
