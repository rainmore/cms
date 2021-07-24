package com.rainmore.cms.domains;

import com.rainmore.cms.domains.users.Account;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Optional;

@MappedSuperclass
abstract class Createable implements Domain {

    private LocalDateTime createdAt;
    private Account createdBy;

    @Column(nullable = false, updatable = false)
    @NotNull
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, updatable = false)
    @NotNull
    public Account getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Account createdBy) {
        this.createdBy = createdBy;
    }

    @PrePersist
    public void prePersist() {
        if (Optional.ofNullable(getCreatedAt()).isEmpty()) {
            setCreatedAt(LocalDateTime.now().withNano(0));
        }
    }

}
