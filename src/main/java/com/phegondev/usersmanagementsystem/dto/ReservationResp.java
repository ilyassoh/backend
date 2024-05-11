package com.phegondev.usersmanagementsystem.dto;

import com.phegondev.usersmanagementsystem.model.ReservationModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResp {
    private Long id;
    private String date_entree;
    private String date_sortie;
    private String status; // Modifier le type de status en String
    private Long placeId;
    private Long parkingId;
    private Long clientId;

    public ReservationResp(ReservationModel reservationModel) {
        this.id = reservationModel.getId();
        this.date_entree = reservationModel.getDate_entree().toString();
        this.date_sortie = reservationModel.getDate_sortie() != null ? reservationModel.getDate_sortie().toString() : null;
        this.status = reservationModel.getStatus() != null ? reservationModel.getStatus().toString() : null; // Vérifiez si status est null avant de l'assigner
        this.placeId = reservationModel.getPlace().getId();
        this.parkingId = reservationModel.getParking().getId();
        this.clientId = reservationModel.getClient().getId();
    }
}

