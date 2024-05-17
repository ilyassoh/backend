package com.phegondev.usersmanagementsystem.repository;

import com.phegondev.usersmanagementsystem.model.ReservationModel;
import com.phegondev.usersmanagementsystem.model.enumm.Status_Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRep extends JpaRepository<ReservationModel, Long> {
    List<ReservationModel> findAll();
    List<ReservationModel> findAllByClientId(Long clientId);
    Page<ReservationModel> findAllByClientId(Long clientId,Pageable p );
    List<ReservationModel> findByPlaceId(Long placeId);
    List<ReservationModel> findByParkingId(Long parkingId);
    @Query("SELECT r FROM ReservationModel r WHERE r.place.id = :placeId AND r.status = :status")
    List<ReservationModel> findByPlaceIdAndStatus(@Param("placeId") Long placeId, @Param("status") Status_Reservation status);
    ReservationModel getReservationById(Long id);
}
