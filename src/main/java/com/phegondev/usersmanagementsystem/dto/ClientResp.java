package com.phegondev.usersmanagementsystem.dto;

import com.phegondev.usersmanagementsystem.model.ClientModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientResp {
    private Long id;
    private Long numeroMatricule;
    private Long numeroTelephone;
    private String adresse;
    private String nom;
    private String email;
    private String genre;
    private int age;

    public ClientResp(ClientModel clientModel) {
        this.id = clientModel.getId();
        this.numeroMatricule = clientModel.getNumeroMatricule();
        this.numeroTelephone = clientModel.getNumeroTelephone();
        this.adresse = clientModel.getAdresse();
        this.nom = clientModel.getNom();
        this.email = clientModel.getEmail();
        this.genre = clientModel.getGenre();
        this.age = clientModel.getAge();
    }
}
