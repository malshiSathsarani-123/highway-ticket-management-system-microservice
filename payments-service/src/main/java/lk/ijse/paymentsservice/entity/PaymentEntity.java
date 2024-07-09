package lk.ijse.paymentsservice.entity;

import jakarta.persistence.*;
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
    private String id;
    private String type;
    private String date;
    private String amount;
    private Pay status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ticketId", referencedColumnName = "id")
    private TicketEntity ticketEntity;
}
