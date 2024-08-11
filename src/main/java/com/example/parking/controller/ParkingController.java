package com.example.parking.controller;

import com.example.parking.dto.ParkingSpaceDTO;
import com.example.parking.service.ParkingSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parking-spaces")
public class ParkingController {

    private final ParkingSpaceService parkingSpaceService;

    @Autowired
    public ParkingController(ParkingSpaceService parkingSpaceService) {
        this.parkingSpaceService = parkingSpaceService;
    }

    @PostMapping
    public ResponseEntity<ParkingSpaceDTO> createParkingSpace(@RequestBody ParkingSpaceDTO parkingSpaceDTO) {
        ParkingSpaceDTO createdParkingSpace = parkingSpaceService.createParkingSpace(parkingSpaceDTO);
        return new ResponseEntity<>(createdParkingSpace, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ParkingSpaceDTO>> getAllAvailableSpaces() {
        List<ParkingSpaceDTO> availableSpaces = parkingSpaceService.getAllAvailableSpaces();
        return new ResponseEntity<>(availableSpaces, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingSpaceDTO> getParkingSpaceById(@PathVariable Long id) {
        ParkingSpaceDTO parkingSpaceDTO = parkingSpaceService.getParkingSpaceById(id);
        if (parkingSpaceDTO != null) {
            return new ResponseEntity<>(parkingSpaceDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingSpaceDTO> updateParkingSpace(@PathVariable Long id, @RequestBody ParkingSpaceDTO parkingSpaceDTO) {
        ParkingSpaceDTO updatedParkingSpace = parkingSpaceService.updateParkingSpace(id, parkingSpaceDTO);
        if (updatedParkingSpace != null) {
            return new ResponseEntity<>(updatedParkingSpace, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParkingSpace(@PathVariable Long id) {
        parkingSpaceService.deleteParkingSpace(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
