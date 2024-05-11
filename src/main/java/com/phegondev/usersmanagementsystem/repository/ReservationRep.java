package com.phegondev.usersmanagementsystem.repository;

import com.phegondev.usersmanagementsystem.model.ReservationModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRep extends JpaRepository<ReservationModel, Long> {
    List<ReservationModel> findAll();
    List<ReservationModel> findAllByClientId(Long clientId);
    Page<ReservationModel> findAllByClientId(Long clientId,Pageable p );
}
