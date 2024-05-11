package com.phegondev.usersmanagementsystem.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequest {
    @NotNull
    private Long placeId;
    @NotNull
    private Long parkingId;
    @NotNull
    private Long clientId;
    @NotNull
    private String status;
}
