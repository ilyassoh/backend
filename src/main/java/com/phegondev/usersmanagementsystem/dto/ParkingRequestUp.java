package com.phegondev.usersmanagementsystem.dto;

import com.phegondev.usersmanagementsystem.model.enumm.Status;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingRequestUp {
    @NotNull
    private String nom;
    @NotNull
    private String emplacement;
    @NotNull
    private int capaciteTotale;
    @NotNull
    private int placesDisponibles;
    @NotNull
    private Status status;
}
