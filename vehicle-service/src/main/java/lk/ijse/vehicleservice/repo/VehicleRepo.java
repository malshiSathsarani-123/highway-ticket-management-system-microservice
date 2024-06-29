package lk.ijse.vehicleservice.repo;

import lk.ijse.vehicleservice.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VehicleRepo extends JpaRepository<VehicleEntity,String> {
    @Query("SELECT MAX(v.vehicleNumber) FROM VehicleEntity v")
    String findMaxId();
}
