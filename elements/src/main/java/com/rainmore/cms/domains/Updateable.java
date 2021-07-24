package com.rainmore.cms.domains;

import com.rainmore.cms.domains.users.Account;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;

@MappedSuperclass
public abstract class Updateable extends Createable {

    private LocalDateTime updatedAt;
    private Account       updatedBy;

    @Column(nullable = false)
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public Account getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Account updatedBy) {
        this.updatedBy = updatedBy;
    }

    @PrePersist
    public void prePersist() {
        super.prePersist();

        if (Optional.ofNullable(getUpdatedAt()).isEmpty()) {
            setUpdatedAt(getCreatedAt());
        }
    }

    @PreUpdate
    public void preUpdate() {
        if (Optional.ofNullable(getUpdatedAt()).isEmpty()) {
            setUpdatedAt(LocalDateTime.now().withNano(0));
        }
    }

    public void updateBy(Account account) {
        preUpdate();
        setUpdatedBy(account);

        if (Optional.ofNullable(getCreatedBy()).isEmpty()) {
            prePersist();
            setCreatedBy(account);
        }
    }

}
