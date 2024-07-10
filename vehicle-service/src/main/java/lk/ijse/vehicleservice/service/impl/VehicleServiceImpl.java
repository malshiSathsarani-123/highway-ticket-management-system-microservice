package lk.ijse.vehicleservice.service.impl;

import lk.ijse.vehicleservice.dto.VehicleDTO;
import lk.ijse.vehicleservice.entity.VehicleEntity;
import lk.ijse.vehicleservice.exception.NotFoundException;
import lk.ijse.vehicleservice.repo.VehicleRepo;
import lk.ijse.vehicleservice.service.VehicleService;
import lk.ijse.vehicleservice.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private final VehicleRepo vehicleRepo;

    @Autowired
    private final Mapping mapping;

    @Override
    public ResponseEntity<?> crateTicket(VehicleDTO vehicleDTO) {
        vehicleRepo.save(mapping.toVehicle(vehicleDTO));
        return ResponseEntity.ok("Vehicle Registered!!!!");
    }
    @Override
    public ResponseEntity<?> updateVehicle(VehicleDTO vehicleDTO) {
        try {
            Optional<VehicleEntity> tmpVehicle = vehicleRepo.findById(vehicleDTO.getVehicleNumber());
            if (!tmpVehicle.isPresent()) {
                throw new NotFoundException("VEHICLE NOT FOUND");
            }

            VehicleEntity vehicleEntity = tmpVehicle.get();
            vehicleEntity.setColor(vehicleDTO.getColor());
            vehicleEntity.setProvince(vehicleDTO.getProvince());
            vehicleEntity.setDescription(vehicleDTO.getDescription());

            vehicleRepo.save(vehicleEntity);

            return ResponseEntity.ok("VEHICLE INFORMATION UPDATED!!!!");
        } catch (DataIntegrityViolationException exception) {
            return ResponseEntity.ok("VEHICLE INFORMATION NOT UPDATED!!!!");
        }
    }

    @Override
    public ResponseEntity<?> retrieval() {
        try {
            List<VehicleEntity> vehicleEntities = vehicleRepo.findAll();
            List<VehicleDTO> vehicleDTOS = mapToDTOList(vehicleEntities);
            return ResponseEntity.ok(vehicleDTOS);
        }catch (DataIntegrityViolationException exception){
            return ResponseEntity.ok("VEHICLE INFORMATION RETRIEVAL FAILED!!!!");
        }
    }

    public VehicleDTO mapToDTO(VehicleEntity vehicleEntity) {
        return new VehicleDTO(
                vehicleEntity.getVehicleNumber(),
                vehicleEntity.getProvince(),
                vehicleEntity.getDescription(),
                vehicleEntity.getColor()
        );
    }

    public List<VehicleDTO> mapToDTOList(List<VehicleEntity> vehicleEntities) {
        return vehicleEntities.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

}
