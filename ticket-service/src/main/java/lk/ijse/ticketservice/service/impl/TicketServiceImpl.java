package lk.ijse.ticketservice.service.impl;

import lk.ijse.ticketservice.dto.TicketDTO;
import lk.ijse.ticketservice.repo.TicketRepo;
import lk.ijse.ticketservice.service.TicketService;
import lk.ijse.ticketservice.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private final TicketRepo ticketRepo;

    @Autowired
    private final Mapping mapping;

    @Override
    public TicketDTO crateTicket(TicketDTO ticketDTO) {
        ticketDTO.setId(nextTicketId());
        return mapping.toTicketDTO(ticketRepo.save(mapping.toTicket(ticketDTO)));

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
