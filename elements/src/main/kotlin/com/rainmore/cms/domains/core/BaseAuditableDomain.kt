package com.rainmore.cms.domains.core

import com.rainmore.cms.domains.Domain
import com.rainmore.cms.domains.core.users.Account
import javax.persistence.*
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseAuditableDomain<T : Domain> : CreateableDomain() {

    @Column(name = "updatedAt", nullable = false)
    var updatedAt: LocalDateTime? = null

    @ManyToOne(targetEntity = Account::class, fetch = FetchType.LAZY)
    @JoinColumn(name = "updatedBy", nullable = false)
    var updatedBy: Account? = null

    @PrePersist
    override fun prePersist() {
        super.prePersist()
        updatedAt = createdAt
    }

    @PreUpdate
    fun preUpdate() {
        updatedAt = LocalDateTime.now().withNano(0)
    }

    @Transient
    @Suppress("UNCHECKED_CAST")
    fun update(account: Account): T {
        updatedAt = LocalDateTime.now().withNano(0)
        updatedBy = account

        if (createdBy == null) {
            createdAt = LocalDateTime.now().withNano(0)
            createdBy = account
        }

        return this as T
    }
}