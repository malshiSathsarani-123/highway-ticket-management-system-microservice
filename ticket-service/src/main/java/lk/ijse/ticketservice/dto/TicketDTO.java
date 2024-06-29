package lk.ijse.ticketservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketDTO {
    private String id;
    private String vehicleNumber;
    private String diverName;
    private String direction;
    private Double amount;
    private String status;
}
