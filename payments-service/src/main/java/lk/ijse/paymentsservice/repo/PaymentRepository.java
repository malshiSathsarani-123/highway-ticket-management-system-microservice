package lk.ijse.paymentsservice.repo;

import lk.ijse.paymentsservice.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity,String> {
}
