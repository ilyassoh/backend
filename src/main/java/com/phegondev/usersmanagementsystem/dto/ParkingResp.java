package com.phegondev.usersmanagementsystem.dto;

import com.phegondev.usersmanagementsystem.model.ParkingModel;
import com.phegondev.usersmanagementsystem.model.ReservationModel;
import com.phegondev.usersmanagementsystem.model.enumm.Status;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingResp {
    private Long id;
    private String nom;
    private String emplacement;
    private int capaciteTotale;
    private int placesDisponibles;
    private Status status;
    @SneakyThrows
    public ParkingResp(ParkingModel parkingModel) {
        this.id = parkingModel.getId();
        this.nom = parkingModel.getNom();
        this.emplacement = parkingModel.getEmplacement();
        this.capaciteTotale = parkingModel.getCapaciteTotale();
        this.placesDisponibles = parkingModel.getPlacesDisponibles();
        this.status = parkingModel.getStatus();
    }

    public ParkingResp(ReservationModel reservationModel) {
    }
}
