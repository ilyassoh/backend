package com.phegondev.usersmanagementsystem.controller;

import com.phegondev.usersmanagementsystem.dto.ReservationRequest;
import com.phegondev.usersmanagementsystem.dto.ReservationRequestUp;
import com.phegondev.usersmanagementsystem.dto.ReservationResp;
import com.phegondev.usersmanagementsystem.model.ReservationModel;
import com.phegondev.usersmanagementsystem.service.ReservationService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/reservation")
@RequiredArgsConstructor
public class ReservationController {
    @GetMapping("/status")
    public ResponseEntity<List<String>> getallStatus() {
        return ResponseEntity.ok(reservationService.getallStatus());
    }
    @GetMapping
    public ResponseEntity<List<ReservationResp>> getAllReservations(
            @RequestParam(defaultValue = "")String search)
    {
        List<ReservationModel> reservations = reservationService.getAllReservations(search);
        List<ReservationResp> reservationsResp = new ArrayList<>();
        for (ReservationModel res : reservations) {
            reservationsResp.add(new ReservationResp(res));
        }
        return ResponseEntity.ok(reservationsResp);
    }
    @GetMapping("/page")
    public ResponseEntity<Page<ReservationResp>>
    findAllPage(@RequestParam(defaultValue = "0") int page,
                @RequestParam(defaultValue = "8") int size,
                @RequestParam(defaultValue = "")String search
    ){
        Pageable pageable = PageRequest.of(page, size);
        Page<ReservationModel> reservations = reservationService.findAllPage(search, pageable);
        return  ResponseEntity.ok(reservations.map(ReservationResp::new));
    }
    @GetMapping("/client/{id}")
    public ResponseEntity<List<ReservationResp>> getReservationsByClientId(@PathVariable Long id) {
        List<ReservationModel> reservations = reservationService.getReservationsByClientId(id);
        List<ReservationResp> reservationsResp = new ArrayList<>();
        for (ReservationModel res : reservations) {
            reservationsResp.add(new ReservationResp(res));
        }
        return ResponseEntity.ok(reservationsResp);
    }
    @GetMapping("/place/{id}")
    public ResponseEntity<List<ReservationResp>> getReservationsByPlaceId(@PathVariable Long id) {
        List<ReservationModel> reservations = reservationService.getReservationsByPlaceId(id);
        List<ReservationResp> reservationsResp = new ArrayList<>();
        for (ReservationModel res : reservations) {
            reservationsResp.add(new ReservationResp(res));
        }
        return ResponseEntity.ok(reservationsResp);
    }
    @GetMapping("/parking/{id}")
    public ResponseEntity<List<ReservationResp>> getReservationsByParkingId(@PathVariable Long id) {
        List<ReservationModel> reservations = reservationService.getReservationsByParkingId(id);
        List<ReservationResp> reservationsResp = new ArrayList<>();
        for (ReservationModel res : reservations) {
            reservationsResp.add(new ReservationResp(res));
        }
        return ResponseEntity.ok(reservationsResp);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReservationResp> getReservationById(@PathVariable Long id) {
        ReservationModel reservation = reservationService.getReservationById(id);
        if (reservation != null) {
            return ResponseEntity.ok(new ReservationResp(reservation));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<?> createReservation(@Valid @RequestBody ReservationRequest reservationRequest) {
        ReservationModel reservationModel = reservationService.createReservation(reservationRequest);
        if (reservationModel != null) {
            ReservationResp response = new ReservationResp(reservationModel);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating reservation");
        }
    }
    @Transactional
    @PutMapping("/{id}")
    public  ResponseEntity<?> editReservation(@PathVariable Long id, @Valid @RequestBody ReservationRequestUp reservationRequestUp) {
        ReservationModel reservationModel = reservationService.editReservation(id, reservationRequestUp);
        if (reservationModel != null) {
            ReservationResp response = new ReservationResp(reservationModel);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<ReservationResp> deleteReservation(@PathVariable Long id) {
        ReservationResp response = new ReservationResp(reservationService.deleteReservation(id));
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private final ReservationService reservationService;
}
