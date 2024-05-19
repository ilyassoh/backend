package com.phegondev.usersmanagementsystem.service;

import com.phegondev.usersmanagementsystem.dto.ReservationRequest;
import com.phegondev.usersmanagementsystem.dto.ReservationRequestUp;
import com.phegondev.usersmanagementsystem.model.ClientModel;
import com.phegondev.usersmanagementsystem.model.ParkingModel;
import com.phegondev.usersmanagementsystem.model.PlaceModel;
import com.phegondev.usersmanagementsystem.model.ReservationModel;
import com.phegondev.usersmanagementsystem.model.enumm.Status_Reservation;
import com.phegondev.usersmanagementsystem.repository.ClientRep;
import com.phegondev.usersmanagementsystem.repository.ReservationRep;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRep reservationRep;
    private final ClientRep clientRep;
    public List<ReservationModel> getAllReservations(String search) {
        if (!search.isEmpty()) {
            Long clientId = Long.parseLong(search);
            return reservationRep.findAllByClientId(clientId);
        } else {
            return reservationRep.findAll();
        }
    }
    public Page<ReservationModel> findAllPage(String search, Pageable p) {
        if (!search.isEmpty()) {
            Long clientId = Long.parseLong(search);
            return reservationRep.findAllByClientId(clientId,p);
        } else {
            return reservationRep.findAll(p);
        }
    }
    public List<String> getallStatus(){
        return enumToList(Status_Reservation.values());
    }
    private   <T extends Enum<T>> List<String> enumToList(T[] enumValues) {
        List<String> list = new ArrayList<>();
        for (T enumValue : enumValues) {
            list.add(enumValue.name());
        }
        return list;
    }
    private Status_Reservation getStatus(String input) {
        for (Status_Reservation status_reservation : Status_Reservation.values()) {
            if (status_reservation.name().equalsIgnoreCase(input)) {
                return status_reservation;
            }
        }
        throw new IllegalArgumentException("No enum constant found for input: " + input);
    }
    public List<ReservationModel> getReservationsByClientId(Long clientId) {
        return reservationRep.findAllByClientId(clientId);
    }
    public List<ReservationModel> getReservationsByPlaceId(Long placeId) {
        return reservationRep.findByPlaceId(placeId);
    }

    public List<ReservationModel> getReservationsByParkingId(Long parkingId) {
        return reservationRep.findByParkingId(parkingId);
    }
    public ReservationModel getReservationById(Long id) {
        return reservationRep.findById(id).orElse(null);
    }
    public ReservationModel createReservation(ReservationRequest reservationRequest) {
        Long placeId = reservationRequest.getPlaceId();
        Long parkingId = reservationRequest.getParkingId();
        Long clientId = reservationRequest.getClientId();
        if (clientId == null) {
            throw new IllegalArgumentException("Client ID cannot be null");
        }
        List<ReservationModel> existingReservations = reservationRep.findByPlaceIdAndStatus(placeId, Status_Reservation.confirmée);
        if (!existingReservations.isEmpty()) {
            throw new IllegalArgumentException("The place is already reserved");
        }
        ClientModel client = clientRep.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Client not found with ID: " + clientId));
        PlaceModel place = new PlaceModel();
        place.setId(placeId);
        ParkingModel parking = new ParkingModel();
        parking.setId(parkingId);
        ReservationModel reservation = new ReservationModel();
        reservation.setPlace(place);
        reservation.setParking(parking);
        reservation.setClient(client);
        reservation.setDate_entree(LocalDateTime.now());
        String statusString = reservationRequest.getStatus();
        if (statusString != null) {
            Status_Reservation statusReservation = getStatus(statusString);
            reservation.setStatus(statusReservation);
        } else {
            reservation.setStatus(Status_Reservation.en_attente); // default status
        }

        return reservationRep.save(reservation);
    }

    @Transactional
    public ReservationModel editReservation(Long id, ReservationRequestUp reservationRequestUp) {
        Optional<ReservationModel> existingReservationOptional = reservationRep.findById(id);
        if (existingReservationOptional.isEmpty()) {
            return null;
        }
        ReservationModel reservation = existingReservationOptional.get();
        Long placeId = reservationRequestUp.getPlaceId();
        Long parkingId = reservationRequestUp.getParkingId();
        Long clientId = reservationRequestUp.getClientId();
        if (clientId == null) {
            throw new IllegalArgumentException("Client ID cannot be null");
        }
        ClientModel client = clientRep.findById(clientId).orElseThrow(()
                -> new IllegalArgumentException("Client not found with ID: " + clientId));
        PlaceModel place = new PlaceModel();
        place.setId(placeId);
        reservation.setPlace(place);
        ParkingModel parking = new ParkingModel();
        parking.setId(parkingId);
        reservation.setParking(parking);
        reservation.setClient(client);
        reservation.setDate_entree(LocalDateTime.now());
        String statusString = String.valueOf(reservationRequestUp.getStatus());
        if (statusString != null) {
            Status_Reservation statusReservation = getStatus(statusString);
            reservation.setStatus(statusReservation);
        } else {
            reservation.setStatus(Status_Reservation.en_attente); // ou tout autre statut par défaut que vous souhaitez utiliser
        }
        return reservationRep.save(reservation);
    }
    @Transactional
    public ReservationModel deleteReservation(Long id) {
        Optional<ReservationModel> reservationOptional = reservationRep.findById(id);
        if (reservationOptional.isPresent()) {
            ReservationModel reservation = reservationOptional.get();
            ClientModel client = reservation.getClient();
            if (client != null) {
                clientRep.delete(client);
            }
            reservationRep.deleteById(id);
            return reservation;
        }
        return null;
    }
    }

