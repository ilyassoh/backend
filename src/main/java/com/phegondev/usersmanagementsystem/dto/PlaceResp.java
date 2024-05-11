package com.phegondev.usersmanagementsystem.dto;

import com.phegondev.usersmanagementsystem.model.PlaceModel;
import com.phegondev.usersmanagementsystem.model.enumm.Status_Place;
import com.phegondev.usersmanagementsystem.model.enumm.Type_Place;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaceResp {
    private Long id;
    private LocalDateTime dateCreation;
    private Status_Place status;
    private Type_Place type;

    public PlaceResp(PlaceModel placeModel) {
        this.id = placeModel.getId();
        this.dateCreation = placeModel.getDateCreation();
        this.status = placeModel.getStatus();
        this.type = placeModel.getType();
    }
}
