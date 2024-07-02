package lk.ijse.vehicleservice.service;

import lk.ijse.vehicleservice.dto.VehicleDTO;
import org.springframework.http.ResponseEntity;

public interface VehicleService {
    ResponseEntity<?> crateTicket(VehicleDTO vehicleDTO);

    ResponseEntity<?> updateVehicle(VehicleDTO vehicleDTO);

    Object retrieval();
}
