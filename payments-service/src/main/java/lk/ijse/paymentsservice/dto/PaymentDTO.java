package lk.ijse.paymentsservice.dto;

import lk.ijse.paymentsservice.enums.Pay;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentDTO {
    private String paymentId;
    private String paymentType;
    private String paymentDate;
    private String paymentAmount;
    private Pay status;
}
