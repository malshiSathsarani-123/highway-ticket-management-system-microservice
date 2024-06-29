package lk.ijse.ticketservice.repo;

import lk.ijse.ticketservice.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TicketRepo extends JpaRepository<TicketEntity,String> {
    @Query("SELECT MAX(t.id) FROM TicketEntity t")
    String findMaxId();
}
