package com.example.parking.service;

import com.example.parking.dto.ParkingSpaceDTO;
import com.example.parking.entity.ParkingSpace;
import com.example.parking.repository.ParkingSpaceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingSpaceService {

    private final ParkingSpaceRepository parkingSpaceRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ParkingSpaceService(ParkingSpaceRepository parkingSpaceRepository, ModelMapper modelMapper) {
        this.parkingSpaceRepository = parkingSpaceRepository;
        this.modelMapper = modelMapper;
    }

    public ParkingSpaceDTO createParkingSpace(ParkingSpaceDTO parkingSpaceDTO) {
        ParkingSpace parkingSpace = modelMapper.map(parkingSpaceDTO, ParkingSpace.class);
        ParkingSpace savedParkingSpace = parkingSpaceRepository.save(parkingSpace);
        return modelMapper.map(savedParkingSpace, ParkingSpaceDTO.class);
    }

    public List<ParkingSpaceDTO> getAllAvailableSpaces() {
        List<ParkingSpace> parkingSpaces = parkingSpaceRepository.findByOccupiedFalse();
        return parkingSpaces.stream()
                .map(parkingSpace -> modelMapper.map(parkingSpace, ParkingSpaceDTO.class))
                .toList();
    }

    public ParkingSpaceDTO getParkingSpaceById(Long id) {
        Optional<ParkingSpace> parkingSpace = parkingSpaceRepository.findById(id);
        return parkingSpace.map(space -> modelMapper.map(space, ParkingSpaceDTO.class)).orElse(null);
    }

    public ParkingSpaceDTO updateParkingSpace(Long id, ParkingSpaceDTO parkingSpaceDTO) {
        Optional<ParkingSpace> existingParkingSpaceOpt = parkingSpaceRepository.findById(id);
        if (existingParkingSpaceOpt.isPresent()) {
            ParkingSpace existingParkingSpace = existingParkingSpaceOpt.get();
            modelMapper.map(parkingSpaceDTO, existingParkingSpace);
            ParkingSpace updatedParkingSpace = parkingSpaceRepository.save(existingParkingSpace);
            return modelMapper.map(updatedParkingSpace, ParkingSpaceDTO.class);
        }
        return null;
    }

    public void deleteParkingSpace(Long id) {
        parkingSpaceRepository.deleteById(id);
    }
}
