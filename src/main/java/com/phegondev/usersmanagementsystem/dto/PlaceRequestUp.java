package com.phegondev.usersmanagementsystem.dto;

import com.phegondev.usersmanagementsystem.model.enumm.Status_Place;
import com.phegondev.usersmanagementsystem.model.enumm.Type_Place;
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
public class PlaceRequestUp {
    @NotNull
    private Status_Place status;
    @NotNull
    private Type_Place type;
    @NotNull
    private LocalDateTime dateCreation;
}
