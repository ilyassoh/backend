package com.phegondev.usersmanagementsystem.dto;



import com.phegondev.usersmanagementsystem.model.enumm.Status_Reservation;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequestUp {
    @NotNull
    private LocalDateTime date_entree;
    @NotNull
    private LocalDateTime date_sortie;
    @NotNull
    private Status_Reservation status;
    @NotNull
    private Long placeId;
    @NotNull
    private Long parkingId;
    @NotNull
    private Long clientId;
}
