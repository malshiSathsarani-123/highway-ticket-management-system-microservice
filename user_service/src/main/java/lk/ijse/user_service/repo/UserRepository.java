package lk.ijse.user_service.repo;

import lk.ijse.user_service.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,String> {

    Optional<UserEntity> findByEmail(String email);

    @Query("SELECT MAX(u.id) FROM UserEntity u")
    String findMaxId();
}