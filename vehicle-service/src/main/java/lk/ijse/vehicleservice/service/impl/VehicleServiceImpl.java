package lk.ijse.vehicleservice.service.impl;

import lk.ijse.vehicleservice.dto.VehicleDTO;
import lk.ijse.vehicleservice.entity.VehicleEntity;
import lk.ijse.vehicleservice.exception.NotFoundException;
import lk.ijse.vehicleservice.repo.VehicleRepo;
import lk.ijse.vehicleservice.service.VehicleService;
import lk.ijse.vehicleservice.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        System.out.println(vehicleDTO.toString());
        Optional<VehicleEntity> tmpVehicle = vehicleRepo.findById(vehicleDTO.getVehicleNumber());
        System.out.println(tmpVehicle);
        if (!tmpVehicle.isPresent())throw new NotFoundException("VEHICLE NOT FOUND");
        tmpVehicle.get().setColor(vehicleDTO.getColor());
        tmpVehicle.get().setProvince(vehicleDTO.getProvince());
        tmpVehicle.get().setDescription(vehicleDTO.getDescription());
        return ResponseEntity.ok("VEHICLE INFORMATION UPDATED!!!!");
    }

    @Override
    public Object retrieval() {
        List<VehicleEntity> vehicleEntities = vehicleRepo.findAll();
        return ResponseEntity.ok(vehicleEntities);
    }

}
