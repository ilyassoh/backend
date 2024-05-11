package com.phegondev.usersmanagementsystem.controller;

import com.phegondev.usersmanagementsystem.dto.ParkingRequest;
import com.phegondev.usersmanagementsystem.dto.ParkingRequestUp;
import com.phegondev.usersmanagementsystem.dto.ParkingResp;
import com.phegondev.usersmanagementsystem.model.ParkingModel;
import com.phegondev.usersmanagementsystem.service.ParkingService;
import com.phegondev.usersmanagementsystem.dto.ParkingResp;
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
@RequestMapping("/api/parking")
@RequiredArgsConstructor
public class ParkingController {
    @GetMapping("/status")
    public ResponseEntity<List<String>> getallStatus() {
        return ResponseEntity.ok(parkingService.getallStatus());
    }
    @GetMapping("/emplacement/{emplacement}")
    public ResponseEntity<List<ParkingResp>> findByEmplacement(@PathVariable String emplacement) {
        List<ParkingModel> parkings = parkingService.findByEmplacement(emplacement);
        if (parkings != null && !parkings.isEmpty()) {
            List<ParkingResp> parkingsResp = new ArrayList<>();
            for (ParkingModel p : parkings) {
                parkingsResp.add(new ParkingResp(p));
            }
            return ResponseEntity.ok(parkingsResp);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @GetMapping("/nom/{nom}")
    public ResponseEntity<ParkingResp> findByNom(@PathVariable String nom) {
        ParkingResp response = new ParkingResp(parkingService.findByNom(nom));
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<ParkingResp>> findAll(
            @RequestParam(defaultValue = "")String search
    ) {
        List<ParkingModel> parkings = parkingService.findAll(search);
        List<ParkingResp> parkingsResp = new ArrayList<>();
        for (ParkingModel p : parkings) {
            parkingsResp.add(new ParkingResp(p));
        }
        return ResponseEntity.ok(parkingsResp);
    }
    @GetMapping("/page")
    public ResponseEntity<Page<ParkingResp>> findAllPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size,
            @RequestParam(defaultValue = "") String search) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ParkingModel> parkings = parkingService.findAllPage(search, pageable);
        return  ResponseEntity.ok(parkings.map(ParkingResp::new));
    }
    @PostMapping
    public ResponseEntity<ParkingResp> createParking(@Valid @RequestBody ParkingRequest parkingreq) {
        ParkingResp response = new ParkingResp(parkingService.createParking(parkingreq));
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<ParkingResp> editParking(@PathVariable Long id, @Valid @RequestBody ParkingRequestUp parkingrequp) {
        ParkingResp response = new ParkingResp(parkingService.editParking(id, parkingrequp));
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Transactional
    @DeleteMapping("/{id}")
    public  ResponseEntity<ParkingResp> deleteParking(@PathVariable long id) {
        ParkingResp response = new ParkingResp(parkingService.deleteParking(id));
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }




    private final ParkingService parkingService;
}
