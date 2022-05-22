package com.api.parkingcontrol.controllers;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import com.api.parkingcontrol.dtos.ParkingSpotDTO;
import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.services.ParkingSpotService;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parking-spots")
public class ParkingSpotController {

    final ParkingSpotService parkingSpotService;

    public ParkingSpotController(ParkingSpotService parkingSpotService) {
        this.parkingSpotService = parkingSpotService;
    }

    @GetMapping()
    public ResponseEntity<List<ParkingSpotModel>> findAllParkingSpots() {
        List<ParkingSpotModel> data = parkingSpotService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingSpotModel> findParkingSpot(@PathVariable(value = "id") UUID id) {
        ParkingSpotModel data = parkingSpotService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @PostMapping
    public ResponseEntity<ParkingSpotModel> createParkingSpot(@RequestBody @Valid ParkingSpotDTO parkingSpotDTO) {
        ParkingSpotModel parkingSpotModel = new ParkingSpotModel();

        BeanUtils.copyProperties(parkingSpotModel, parkingSpotDTO);

        ParkingSpotModel data = parkingSpotService.save(parkingSpotModel);
        
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(data);
    }

    @PutMapping
    public ResponseEntity<ParkingSpotModel> updateParkingSpot(@PathVariable(value = "id") UUID id, @RequestBody @Valid ParkingSpotDTO parkingSpotDTO) {
        ParkingSpotModel data = parkingSpotService.update(id, parkingSpotDTO);

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteParkingSpot(@PathVariable(value = "id") UUID id) {
        parkingSpotService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).body("Parking spot deleted successfully");
    }

}
