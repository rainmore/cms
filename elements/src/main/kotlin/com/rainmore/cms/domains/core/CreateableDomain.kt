package com.rainmore.cms.domains.core

import com.rainmore.cms.domains.Domain
import com.rainmore.cms.domains.core.users.Account
import javax.persistence.*
import java.time.LocalDateTime

@MappedSuperclass
abstract class CreateableDomain : Domain {

    @Column(name = "createdAt", nullable = false, updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now().withNano(0)

    @ManyToOne(targetEntity = Account::class, fetch = FetchType.LAZY)
    @JoinColumn(name = "createdBy", nullable = false, updatable = false)
    var createdBy: Account? = null

    @PrePersist
    open fun prePersist() {
        createdAt = LocalDateTime.now().withNano(0)
    }

}
