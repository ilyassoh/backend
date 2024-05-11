package com.phegondev.usersmanagementsystem.dto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequest {
    @NotNull
    private Long numeroMatricule;
    @NotNull
    private Long numeroTelephone;
    @NotEmpty
    private String adresse;
    @NotEmpty
    private String nom;
    @NotEmpty
    private String email;
    @NotEmpty
    private String genre;
    @NotNull
    private int age;
}
