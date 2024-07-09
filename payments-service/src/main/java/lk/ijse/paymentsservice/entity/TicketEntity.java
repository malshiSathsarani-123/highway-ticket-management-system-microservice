package lk.ijse.paymentsservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "ticket")
public class TicketEntity {
    @Id
    private String id;
    private String diverName;
    private String direction;
    private Double amount;
    private String status;

    @OneToOne(mappedBy = "ticketEntity")
    private PaymentEntity paymentEntity;

    public TicketEntity(String id, String diverName, String direction, Double amount, String status) {
        this.id = id;
        this.diverName = diverName;
        this.direction = direction;
        this.amount = amount;
        this.status = status;
    }
}
