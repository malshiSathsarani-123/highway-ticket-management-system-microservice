package lk.ijse.vehicleservice.entity;

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicleNumber")
    private VehicleEntity vehicleEntity;
}
