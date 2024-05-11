package com.phegondev.usersmanagementsystem.controller;

import com.phegondev.usersmanagementsystem.dto.PlaceRequest;
import com.phegondev.usersmanagementsystem.dto.PlaceRequestUp;
import com.phegondev.usersmanagementsystem.dto.PlaceResp;
import com.phegondev.usersmanagementsystem.model.PlaceModel;
import com.phegondev.usersmanagementsystem.model.enumm.Status_Place;
import com.phegondev.usersmanagementsystem.model.enumm.Type_Place;
import com.phegondev.usersmanagementsystem.service.PlaceService;
import com.phegondev.usersmanagementsystem.dto.PlaceResp;
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
@RequestMapping("/api/place")
@RequiredArgsConstructor
public class PlaceController {

    @GetMapping("/status")
    public ResponseEntity<List<String>> getallStatus() {
        return ResponseEntity.ok(placeService.getallStatus());
    }
    @GetMapping("/type/{type}")
    public ResponseEntity<List<PlaceResp>> findAllByType(@PathVariable Type_Place type) {
        List<PlaceModel> places = placeService.findAllByType(type);
        if (places != null && !places.isEmpty()) {
            List<PlaceResp> placesResp = new ArrayList<>();
            for (PlaceModel place : places) {
                placesResp.add(new PlaceResp(place));
            }
            return ResponseEntity.ok(placesResp);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @GetMapping("/typeOrStatus")
    public ResponseEntity<List<PlaceResp>> findAllByTypeOrStatus(
            @RequestParam(required = false) Type_Place type,
            @RequestParam(required = false) Status_Place status) {
        List<PlaceModel> places = placeService.findAllByTypeOrStatus(type, status);
        if (places != null && !places.isEmpty()) {
            List<PlaceResp> placesResp = new ArrayList<>();
            for (PlaceModel place : places) {
                placesResp.add(new PlaceResp(place));
            }
            return ResponseEntity.ok(placesResp);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @GetMapping
    public ResponseEntity<List<PlaceResp>> findAll(@RequestParam(defaultValue = "") String search) {
        List<PlaceModel> places = placeService.findAll(search);
        List<PlaceResp> placesResp = new ArrayList<>();
        for (PlaceModel place : places) {
            placesResp.add(new PlaceResp(place));
        }
        return ResponseEntity.ok(placesResp);
    }
    @GetMapping("/status/{status}")
    public ResponseEntity<List<PlaceResp>> findAllByStatus(@PathVariable Status_Place status) {
        List<PlaceModel> places = placeService.findAllByStatus(status);
        if (places != null && !places.isEmpty()) {
            List<PlaceResp> placesResp = new ArrayList<>();
            for (PlaceModel place : places) {
                placesResp.add(new PlaceResp(place));
            }
            return ResponseEntity.ok(placesResp);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @GetMapping("/types")
    public ResponseEntity<List<String>> getAllTypes() {
        return ResponseEntity.ok(placeService.getAllTypes());
    }
    @GetMapping("/page")
    public ResponseEntity<Page<PlaceResp>> findAllPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size,
            @RequestParam(defaultValue = "") String search) {
        Pageable pageable = PageRequest.of(page, size);
        Page<PlaceModel> placesPage = placeService.findAllPage(search, pageable);
        if (placesPage.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(placesPage.map(PlaceResp::new));
    }
    @PostMapping
    public ResponseEntity<PlaceResp> createPlace(@Valid @RequestBody PlaceRequest placeRequest) {
        PlaceModel createdPlace = placeService.createPlace(placeRequest);
        if (createdPlace != null) {
            return ResponseEntity.ok(new PlaceResp(createdPlace));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<PlaceResp> editPlace(@PathVariable Long id, @Valid @RequestBody PlaceRequestUp placeRequestUp) {
        PlaceModel placeModel = placeService.editPlace(id, placeRequestUp);
        if (placeModel != null) {
            PlaceResp response = new PlaceResp(placeModel);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<PlaceResp> deletePlace(@PathVariable Long id) {
        PlaceResp response = new PlaceResp(placeService.deletePlace(id));
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    private final PlaceService placeService;
}
