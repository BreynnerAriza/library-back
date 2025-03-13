package org.library.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.library.role.entity.Role;
import org.library.shared.entity.Audit;
import org.library.user.userstatus.UserStatus;

import java.util.UUID;

@Entity
@Table(name = "user", schema = "main")
@AllArgsConstructor @NoArgsConstructor
@Getter
public class User extends Audit {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id", unique = true, updatable = false)
    private UUID userId;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    //Permite verificar si un usuario se encuentra en estado activo
    public boolean isDisabled(){
        return this.status.equals(UserStatus.DISABLED);
    }

    public User(String username, String password, UserStatus status, Role role) {
        this.username = username;
        this.password = password;
        this.status = status;
        this.role = role;
    }
}
