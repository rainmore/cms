package com.rainmore.cms.domains.core

import com.rainmore.cms.domains.Domain
import com.rainmore.cms.domains.core.users.Account
import java.time.LocalDateTime
import javax.persistence.*

@MappedSuperclass
abstract class ArchivableAuditableDomain<T : Domain> : BaseAuditableDomain<T>() {

    @Column(name = "archivedAt", nullable = true)
    var archivedAt: LocalDateTime? = null

    @ManyToOne(targetEntity = Account::class, fetch = FetchType.LAZY)
    @JoinColumn(name = "archivedBy", nullable = true)
    var archivedBy: Account? = null

    @Transient
    fun isUnarchived(): Boolean = null == archivedAt

    @Transient
    fun isActual(): Boolean = !isUnarchived()

    @Transient
    @Suppress("UNCHECKED_CAST")
    fun archive(archivedBy: Account): T {
        this.archivedBy = archivedBy
        archivedAt = LocalDateTime.now().withNano(0)
        this.update(archivedBy)
        return this as T
    }
}