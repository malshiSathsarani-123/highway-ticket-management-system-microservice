package lk.ijse.paymentsservice.dto;

import lk.ijse.paymentsservice.enums.Pay;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentDTO {
    private String id;
    private String type;
    private String date;
    private String amount;
    private Pay status;
    private String ticketId;
}
