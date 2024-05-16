package com.phegondev.usersmanagementsystem.repository;

import com.phegondev.usersmanagementsystem.model.PlaceModel;
import com.phegondev.usersmanagementsystem.model.enumm.Status_Place;
import com.phegondev.usersmanagementsystem.model.enumm.Type_Place;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRep extends JpaRepository<PlaceModel, Long> {
    List<PlaceModel> findAll();
    List<PlaceModel> findAllByStatus(Status_Place status);
    List<PlaceModel> findAllByType(Type_Place type);
    List<PlaceModel> findAllByTypeOrStatus(Type_Place type, Status_Place status);
    Page<PlaceModel> findAllByTypeOrStatus(Type_Place type, Status_Place status, Pageable p);
    long countAllByStatusAndReservationsIsNull(Status_Place status);
    PlaceModel getPlaceById(Long id); // Méthode ajoutée
}
