package com.phegondev.usersmanagementsystem.model;

import com.phegondev.usersmanagementsystem.model.enumm.Status_Place;
import com.phegondev.usersmanagementsystem.model.enumm.Type_Place;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlaceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private LocalDateTime dateCreation;
    @Enumerated(EnumType.STRING)
    private Status_Place status;
    @Enumerated(EnumType.STRING)
    private Type_Place type;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<ReservationModel> reservations;


}
