package com.phegondev.usersmanagementsystem.service;

import com.phegondev.usersmanagementsystem.dto.ParkingRequest;
import com.phegondev.usersmanagementsystem.dto.ParkingRequestUp;
import com.phegondev.usersmanagementsystem.model.ParkingModel;
import com.phegondev.usersmanagementsystem.model.enumm.Status;
import com.phegondev.usersmanagementsystem.repository.ParkingRep;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParkingService {

    public ParkingModel findByNom(String nom) {
        return parkingRep.findByNom(nom);
    }
    private final ParkingRep parkingRep;
    public List<ParkingModel> findByEmplacement(String emplacement) {
        return parkingRep.findByEmplacement(emplacement);
    }
    public List<ParkingModel> findAll(String se) {
        if(!se.isEmpty()) {
            return parkingRep.findAllByNomContainingOrEmplacementContaining(se,se);
        }
        return parkingRep.findAll();
    }
    public Page<ParkingModel> findAllPage(String se, Pageable p) {
        if(!se.isEmpty()) {
            return parkingRep.findAllByNomContainingOrEmplacementContaining(se,se,p);
        }
        return parkingRep.findAll(p);
    }
    public List<String> getallStatus(){
        return enumToList(Status.values());
    }
    private   <T extends Enum<T>> List<String> enumToList(T[] enumValues) {
        List<String> list = new ArrayList<>();
        for (T enumValue : enumValues) {
            list.add(enumValue.name());
        }
        return list;
    }
    private Status getStatus(String input) {
        for (Status status : Status.values()) {
            if (status.name().equalsIgnoreCase(input)) {
                return status;
            }
        }
        throw new IllegalArgumentException("No enum constant found for input: " + input);
    }
    public ParkingModel createParking(ParkingRequest parkingreq) {
        ParkingModel p = new ParkingModel();
        ParkingModel existing_p = parkingRep.findByNom(parkingreq.getNom());
        if (existing_p != null) {
            return null;
        }
        p.setNom(parkingreq.getNom());
        p.setEmplacement(parkingreq.getEmplacement());
        p.setCapaciteTotale(parkingreq.getCapaciteTotale());
        p.setPlacesDisponibles(parkingreq.getPlacesDisponibles());
        p.setStatus(parkingreq.getStatus());
        p.setDate_ouverture(LocalDateTime.now());
        p.setDate_fermeture(LocalDateTime.now());
        return parkingRep.save(p);
    }
    @Transactional
    public ParkingModel editParking(long id, ParkingRequestUp parkingRequestUp) {
        Optional<ParkingModel> existingParkingOptional = parkingRep.findById(id);
        if (existingParkingOptional.isEmpty()) {
            return null;
        }
        ParkingModel existingParking = existingParkingOptional.get();
        existingParking.setNom(parkingRequestUp.getNom());
        existingParking.setEmplacement(parkingRequestUp.getEmplacement());
        existingParking.setCapaciteTotale(parkingRequestUp.getCapaciteTotale());
        existingParking.setPlacesDisponibles(parkingRequestUp.getPlacesDisponibles());
        existingParking.setStatus(parkingRequestUp.getStatus());
        return parkingRep.save(existingParking);
    }
    @Transactional
    public ParkingModel deleteParking(Long id) {
        Optional<ParkingModel> existingParkingOptional = parkingRep.findById(id);
        if (existingParkingOptional.isEmpty()) {
            return null;
        }
        ParkingModel existingParking = existingParkingOptional.get();
        parkingRep.deleteById(id);
        return existingParking;
    }
}
