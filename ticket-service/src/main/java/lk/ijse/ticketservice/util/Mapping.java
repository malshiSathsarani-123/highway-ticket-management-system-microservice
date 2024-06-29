package lk.ijse.ticketservice.util;

import lk.ijse.ticketservice.dto.TicketDTO;
import lk.ijse.ticketservice.entity.TicketEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Mapping {
    private final ModelMapper mapper;

    public TicketDTO toTicketDTO(TicketEntity ticketEntity) {
       return  mapper.map(ticketEntity, TicketDTO.class);
    }
    public TicketEntity toTicket(TicketDTO ticketDTO) {
        return  mapper.map(ticketDTO, TicketEntity.class);
    }
    public List<TicketDTO> toTicketDTOList(List<TicketEntity> ticketEntities) {
       return mapper.map(ticketEntities, List.class);
    }

}
