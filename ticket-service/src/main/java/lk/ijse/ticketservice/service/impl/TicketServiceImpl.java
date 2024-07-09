package lk.ijse.ticketservice.service.impl;

import lk.ijse.ticketservice.dto.TicketDTO;
import lk.ijse.ticketservice.entity.TicketEntity;
import lk.ijse.ticketservice.entity.VehicleEntity;
import lk.ijse.ticketservice.exception.NotFoundException;
import lk.ijse.ticketservice.repo.TicketRepo;
import lk.ijse.ticketservice.repo.VehicleRepo;
import lk.ijse.ticketservice.service.TicketService;
import lk.ijse.ticketservice.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private final TicketRepo ticketRepo;

    @Autowired
    private final VehicleRepo vehicleRepo;

    @Autowired
    private final Mapping mapping;

    @Override
    public ResponseEntity<?> crateTicket(TicketDTO ticketDTO) {
        ticketDTO.setId(nextTicketId());
        ticketDTO.setStatus("start");
        TicketEntity ticket = mapping.toTicket(ticketDTO);
        Optional<VehicleEntity> byId = vehicleRepo.findById(ticketDTO.getVehicleNumber());
        ticket.setVehicleEntity(new VehicleEntity(ticketDTO.getVehicleNumber(),byId.get().getProvince(),byId.get().getDescription(),byId.get().getColor()));
        mapping.toTicketDTO(ticketRepo.save(ticket));
        return ResponseEntity.ok("Ticket saved!!!!");

    }

    @Override
    public ResponseEntity<?> updateStatus(TicketDTO ticketDTO) {
        try {
            Optional<TicketEntity> tmpTicket = ticketRepo.findById(ticketDTO.getId());
            if (!tmpTicket.isPresent()){
                throw new NotFoundException("Ticket NOT FOUND");
            }
            TicketEntity ticketEntity = tmpTicket.get();
            ticketEntity.setStatus(ticketDTO.getStatus());

            ticketRepo.save(ticketEntity);

            return ResponseEntity.ok("TICKET INFORMATION UPDATED!!!!");
        } catch (DataIntegrityViolationException exception) {
            return ResponseEntity.ok("TICKET INFORMATION NOT UPDATED!!!!");
        }
    }

    @Override
    public ResponseEntity<?> retrieval() {
        List<TicketEntity> ticketEntities = ticketRepo.findAll();
        return ResponseEntity.ok(ticketEntities);
    }

    public String nextTicketId() {
        String maxId = ticketRepo.findMaxId();
        if (maxId != null){
            return generateNextTicketId(maxId);
        }else {
            return "T-001";
        }
    }

    private static String generateNextTicketId(String lastTicketId) {
        String numericPart = lastTicketId.substring(2);
        int nextNumericValue = Integer.parseInt(numericPart) + 1;
        String nextNumericPart = String.format("%03d", nextNumericValue);
        return "T-" + nextNumericPart;
    }
}
