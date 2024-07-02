package lk.ijse.ticketservice.repo;

import lk.ijse.ticketservice.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepo extends JpaRepository<VehicleEntity,String> {

}
