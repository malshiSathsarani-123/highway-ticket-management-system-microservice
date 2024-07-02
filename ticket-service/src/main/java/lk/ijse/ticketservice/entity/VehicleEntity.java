package lk.ijse.ticketservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "vehicle")
public class VehicleEntity {
    @Id
    private String vehicleNumber;
    private String province;
    private String description;
    private String color;

    @OneToMany(mappedBy = "vehicleEntity",cascade = CascadeType.ALL)
    private List<TicketEntity> ticketEntities;

    public VehicleEntity(String vehicleNumber, String province, String description, String color) {
        this.vehicleNumber = vehicleNumber;
        this.province = province;
        this.description = description;
        this.color = color;
    }
}
