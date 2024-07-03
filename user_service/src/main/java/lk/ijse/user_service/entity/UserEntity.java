package lk.ijse.user_service.entity;

import jakarta.persistence.*;
import lk.ijse.user_service.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
    @Id
    private String id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private Role role;
}