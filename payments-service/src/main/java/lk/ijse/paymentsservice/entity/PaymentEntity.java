package lk.ijse.paymentsservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lk.ijse.paymentsservice.enums.Pay;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "payment")
public class PaymentEntity {
    @Id
    private String paymentId;
    private String paymentType;
    private String paymentDate;
    private String paymentAmount;
    private Pay status;
}
