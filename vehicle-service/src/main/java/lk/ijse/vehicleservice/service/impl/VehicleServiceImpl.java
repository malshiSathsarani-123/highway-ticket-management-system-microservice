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
        Optional<VehicleEntity> tmpVehicle = vehicleRepo.findById(vehicleDTO.getVehicleNumber());
        if (!tmpVehicle.isPresent())throw new NotFoundException("VEHICLE NOT FOUND");
        tmpVehicle.get().setColor(vehicleDTO.getColor());
        tmpVehicle.get().setProvince(vehicleDTO.getProvince());
        tmpVehicle.get().setDescription(vehicleDTO.getDescription());
        return ResponseEntity.ok("VEHICLE INFORMATION UPDATED!!!!");
    }
//    @Override
//    public ResponseEntity<?> crateTicket(TicketDTO ticketDTO) {
//        ticketDTO.setId(nextTicketId());
//        ticketDTO.setStatus("start");
//        mapping.toTicketDTO(ticketRepo.save(mapping.toTicket(ticketDTO)));
//        return ResponseEntity.ok("Ticket saved!!!!");
//
//    }
//
//    @Override
//    public ResponseEntity<?> updateStatus(TicketDTO ticketDTO) {
//        Optional<TicketEntity> tmpTicket = ticketRepo.findById(ticketDTO.getId());
//        if (!tmpTicket.isPresent())throw new NotFoundException("Ticket NOT FOUND");
//        tmpTicket.get().setStatus(ticketDTO.getStatus());
//        return ResponseEntity.ok("Ticket status updated!!!!");
//    }
//
//    @Override
//    public ResponseEntity<?> retrieval() {
//        List<TicketEntity> ticketEntities = ticketRepo.findAll();
//        return ResponseEntity.ok(ticketEntities);
//    }
}
