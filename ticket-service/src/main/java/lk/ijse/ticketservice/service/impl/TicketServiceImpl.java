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
import java.util.stream.Collectors;

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
        try {
            ticketDTO.setId(nextTicketId());
            ticketDTO.setStatus("start");
            TicketEntity ticket = mapping.toTicket(ticketDTO);
            Optional<VehicleEntity> byId = vehicleRepo.findById(ticketDTO.getVehicleNumber());
            if (!byId.isPresent()) {
                return ResponseEntity.ok("VEHICLE IS NOT EXIST!!!!");
            }
            ticket.setVehicleEntity(new VehicleEntity(ticketDTO.getVehicleNumber(),byId.get().getProvince(),byId.get().getDescription(),byId.get().getColor()));
            mapping.toTicketDTO(ticketRepo.save(ticket));
            return ResponseEntity.ok("TICKET SAVED!!!!");
        } catch (DataIntegrityViolationException exception) {
            return ResponseEntity.ok("TICKET NOT SAVED!!!!");
        }
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
        try {
            List<TicketEntity> ticketEntities = ticketRepo.findAll();
            List<TicketDTO> ticketDTOS = mapToDTOList(ticketEntities);
            return ResponseEntity.ok(ticketDTOS);
        }catch (DataIntegrityViolationException exception){
            return ResponseEntity.ok("TICKET INFORMATION RETRIEVAL FAILED!!!!");
        }
    }
    public TicketDTO mapToDTO(TicketEntity ticketEntity) {
        return new TicketDTO(
                ticketEntity.getId(),
                ticketEntity.getVehicleEntity().getVehicleNumber(),
                ticketEntity.getDiverName(),
                ticketEntity.getDirection(),
                ticketEntity.getAmount(),
                ticketEntity.getStatus()
        );
    }

    public List<TicketDTO> mapToDTOList(List<TicketEntity> ticketEntities) {
        return ticketEntities.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
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
