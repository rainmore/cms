package com.rainmore.cms.domains.system.security.auth;

import com.rainmore.cms.domains.HasId;
import com.rainmore.cms.domains.users.Account;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Entity(name = "system.security.auth.forgetPasswordRequests")
@Table(name = "userForgetPasswordRequests")
public class ForgetPasswordRequest implements HasId<UUID> {

    private UUID id;
    private LocalDateTime createdAt;
    private Account       account;

    @Id
    @Column(name = "id", unique = true, nullable = false, updatable = false, length = 36)
    @Type(type = "uuid-char")
    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }

    @Column(nullable = false, updatable = false)
    @NotNull
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @OneToOne
    @JoinColumn(name = "userAccountId", nullable = false, updatable = false)
    @NotNull
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @PrePersist
    public void prePersist() {
        if (Optional.ofNullable(getId()).isEmpty()) {
            setId(UUID.randomUUID());
        }

        if (Optional.ofNullable(getCreatedAt()).isEmpty()) {
            setCreatedAt(LocalDateTime.now().withNano(0));
        }
    }

}
