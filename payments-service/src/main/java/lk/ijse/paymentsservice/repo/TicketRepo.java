package lk.ijse.paymentsservice.repo;

import lk.ijse.paymentsservice.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepo extends JpaRepository<TicketEntity,String> {

}
