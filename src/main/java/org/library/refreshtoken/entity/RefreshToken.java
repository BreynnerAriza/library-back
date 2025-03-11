package org.library.refreshtoken.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.library.shared.entity.Audit;
import org.library.user.entity.User;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "refresh_token", schema = "main")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class RefreshToken extends Audit {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "refresh_token_id", updatable = false)
    private UUID refreshTokenId;

    @Column(name = "token", unique = true, nullable = false)
    private String token;

    @Column(name = "expired_at", nullable = false, updatable = false)
    private LocalDateTime expiredAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    public RefreshToken(Long refreshTokenDuration, User user) {
        this.token = UUID.randomUUID().toString();
        this.expiredAt = calculateExpiredAt(refreshTokenDuration);
        this.user = user;
    }

    public boolean isValid(){
        return expiredAt.isBefore(LocalDateTime.now());
    }

    private LocalDateTime calculateExpiredAt(Long refreshTokenDuration){
        return LocalDateTime.now().plus(Duration.ofMinutes(refreshTokenDuration));
    }

}
