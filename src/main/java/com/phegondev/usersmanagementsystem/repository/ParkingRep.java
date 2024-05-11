package com.phegondev.usersmanagementsystem.repository;

import com.phegondev.usersmanagementsystem.model.ParkingModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingRep extends JpaRepository<ParkingModel, Long> {

    ParkingModel findByNom(String nom);
    List<ParkingModel> findByEmplacement(String emplacement);
    List<ParkingModel>findAllByNomContainingOrEmplacementContaining(String nom,String emplacement);
    Page<ParkingModel> findAllByNomContainingOrEmplacementContaining(String nom, String emplacement, Pageable p);

}
