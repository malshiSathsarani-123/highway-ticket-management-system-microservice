package lk.ijse.paymentsservice.repo;

import lk.ijse.paymentsservice.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaymentRepo extends JpaRepository<PaymentEntity,String> {
    @Query("SELECT MAX(p.id) FROM PaymentEntity p")
    String findMaxId();
}
