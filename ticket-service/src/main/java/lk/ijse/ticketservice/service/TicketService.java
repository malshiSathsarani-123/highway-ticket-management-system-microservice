package lk.ijse.ticketservice.service;

import lk.ijse.ticketservice.dto.TicketDTO;
import org.springframework.http.ResponseEntity;

public interface TicketService {
    ResponseEntity<?> crateTicket(TicketDTO ticketDTO);

    ResponseEntity<?> updateStatus(TicketDTO ticketDTO);

    ResponseEntity<?> retrieval();
}
