package lk.ijse.vehicleservice.util;

import lk.ijse.vehicleservice.dto.TicketDTO;
import lk.ijse.vehicleservice.dto.VehicleDTO;
import lk.ijse.vehicleservice.entity.TicketEntity;
import lk.ijse.vehicleservice.entity.VehicleEntity;
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

    public VehicleDTO toVehicleDTO(VehicleEntity vehicleEntity) {
        return  mapper.map(vehicleEntity, VehicleDTO.class);
    }
    public VehicleEntity toVehicle(VehicleDTO vehicleDTO) {
        return  mapper.map(vehicleDTO, VehicleEntity.class);
    }
    public List<VehicleDTO> toVehicleDTOList(List<VehicleEntity> vehicleEntities) {
        return mapper.map(vehicleEntities, List.class);
    }

}
