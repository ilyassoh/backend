package com.phegondev.usersmanagementsystem.service;

import com.phegondev.usersmanagementsystem.dto.PlaceRequest;
import com.phegondev.usersmanagementsystem.dto.PlaceRequestUp;
import com.phegondev.usersmanagementsystem.model.enumm.Status_Place;
import com.phegondev.usersmanagementsystem.model.enumm.Type_Place;
import com.phegondev.usersmanagementsystem.model.PlaceModel;
import com.phegondev.usersmanagementsystem.repository.PlaceRep;
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
public class PlaceService {
    public List<PlaceModel> findAllByType(Type_Place type) {
        if (type != null) {
            return placeRep.findAllByType(type);
        } else {
            return placeRep.findAll();
        }
    }
    public List<PlaceModel> findAll(String se) {
        if(!se.isEmpty()) {
            return placeRep.findAllByTypeOrStatus(Type_Place.valueOf(se),Status_Place.valueOf(se));
        }
        return placeRep.findAll();
    }
    public List<PlaceModel> findAllByStatus(Status_Place status) {
        if (status != null) {
            return placeRep.findAllByStatus(status);
        } else {
            return placeRep.findAll();
        }
    }
    public List<String> getallStatus(){
        return enum_sToList(Status_Place.values());
    }
    private   <T extends Enum<T>> List<String> enum_sToList(T[] enumValues) {
        List<String> list = new ArrayList<>();
        for (T enumValue : enumValues) {
            list.add(enumValue.name());
        }
        return list;
    }
    public List<String> getAllTypes(){
        return enum_tToList(Type_Place.values());
    }
    private   <T extends Enum<T>> List<String> enum_tToList(T[] enumValues) {
        List<String> list = new ArrayList<>();
        for (T enumValue : enumValues) {
            list.add(enumValue.name());
        }
        return list;
    }
    public Page<PlaceModel> findAllPage(String se, Pageable p) {
        if (!se.isEmpty()) {
            return placeRep.findAllByTypeOrStatus(Type_Place.valueOf(se), Status_Place.valueOf(se), p);
        }
        return placeRep.findAll(p);
    }
    public PlaceModel createPlace(PlaceRequest placeRequest) {
        if (placeRequest.getId() != null && placeRep.existsById(placeRequest.getId())) {
            return null;
        }
        PlaceModel place = new PlaceModel();
        place.setStatus(placeRequest.getStatus());
        place.setType(placeRequest.getType());
        place.setDateCreation(LocalDateTime.now());
        return placeRep.save(place);
    }
    @Transactional
    public PlaceModel editPlace(Long id, PlaceRequestUp placeRequestUp) {
        Optional<PlaceModel> existingPlaceOptional = placeRep.findById(id);
        if (existingPlaceOptional.isEmpty()) {
            return null;
        }
        PlaceModel existingPlace = existingPlaceOptional.get();
        existingPlace.setStatus(placeRequestUp.getStatus());
        existingPlace.setType(placeRequestUp.getType());
        existingPlace.setDateCreation(placeRequestUp.getDateCreation());
        return placeRep.save(existingPlace);
    }
    @Transactional
    public PlaceModel deletePlace(Long id) {
        Optional<PlaceModel> existingPlaceOptional = placeRep.findById(id);
        if (existingPlaceOptional.isEmpty()) {
            return null;
        }
        PlaceModel existingPlace = existingPlaceOptional.get();
        placeRep.deleteById(id);
        return existingPlace;
    }
    public PlaceModel getPlaceById(Long id) {
        return placeRep.findById(id).orElse(null);
    }
    public long countEmptyUnreservedPlaces() {
        return placeRep.countAllByStatusAndReservationsIsNull(Status_Place.vide);
    }
    private final PlaceRep placeRep;


}
