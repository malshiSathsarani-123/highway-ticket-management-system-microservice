package lk.ijse.vehicleservice.service.impl;

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

    public String nextVehicleId() {
        String maxId = vehicleRepo.findMaxId();
        if (maxId != null){
            return generateNextVehicleId(maxId);
        }else {
            return "V-001";
        }
    }

    private static String generateNextVehicleId(String lastVehicleId) {
        String numericPart = lastVehicleId.substring(2);
        int nextNumericValue = Integer.parseInt(numericPart) + 1;
        String nextNumericPart = String.format("%03d", nextNumericValue);
        return "T-" + nextNumericPart;
    }
}
