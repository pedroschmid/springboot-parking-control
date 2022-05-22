package com.api.parkingcontrol.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import com.api.parkingcontrol.dtos.ParkingSpotDTO;
import com.api.parkingcontrol.interfaces.ParkingSpotServiceInterface;
import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.repositories.ParkingSpotRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ParkingSpotService implements ParkingSpotServiceInterface {
    
    final ParkingSpotRepository parkingSpotRepository;
    
    public ParkingSpotService(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
    }

    public List<ParkingSpotModel> findAll() {
        return parkingSpotRepository.findAll();
    }

    public ParkingSpotModel findById(UUID id) {
        ParkingSpotModel parkingSpotModel = validateParkingSpotExistence(id);

        return parkingSpotModel;
    }

    @Transactional
    public ParkingSpotModel save(ParkingSpotModel parkingSpotModel) {
        try {
            return parkingSpotRepository.save(parkingSpotModel);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error while creating parking spot", e);
        }
    }

    @Transactional
    public ParkingSpotModel update(UUID id, ParkingSpotDTO parkingSpotDTO) {
        try {
            ParkingSpotModel parkingSpotModel = validateParkingSpotExistence(id);

            BeanUtils.copyProperties(parkingSpotDTO, parkingSpotModel);
    
            return parkingSpotRepository.save(parkingSpotModel);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error while updating parking spot", e);
        }
    }

    @Transactional
    public void delete(UUID id) {
        ParkingSpotModel parkingSpotModel = validateParkingSpotExistence(id);

        parkingSpotRepository.delete(parkingSpotModel);
    }

    private ParkingSpotModel validateParkingSpotExistence(UUID id) {
        Optional<ParkingSpotModel> optionalParkingSpot = parkingSpotRepository.findById(id);

        if (!optionalParkingSpot.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Parking spot not found");
        }

        return optionalParkingSpot.get();
    }
}
