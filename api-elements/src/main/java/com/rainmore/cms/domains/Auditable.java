package com.rainmore.cms.domains;

import com.rainmore.cms.domains.users.Account;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;

@MappedSuperclass
public abstract class Auditable extends Updateable {

    private LocalDateTime archivedAt;
    private Account       archivedBy;

    @Column
    public LocalDateTime getArchivedAt() {
        return archivedAt;
    }

    public void setArchivedAt(LocalDateTime archivedAt) {
        this.archivedAt = archivedAt;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "archivedBy")
    public Account getArchivedBy() {
        return archivedBy;
    }

    public void setArchivedBy(Account archivedBy) {
        this.archivedBy = archivedBy;
    }

    @Transient
    public void archive(Account archivedBy) {
        setArchivedAt(LocalDateTime.now().withNano(0));
        setArchivedBy(archivedBy);
        updateBy(archivedBy);
    }

    @Transient
    public Boolean isActual() {
        return Optional.ofNullable(getArchivedAt()).isPresent();
    }

}
