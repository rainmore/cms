package com.rainmore.cms.domains.core.users

import com.rainmore.cms.domains.Domain
import org.hibernate.annotations.Type
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity(name = "core.users.forgetPasswordRequests")
@Table(name = "userForgetPasswordRequests")
data class ForgetPasswordRequest
(
        @Id
        @Column(name = "id", unique = true, nullable = false, updatable = false, length = 36)
        @Type(type = "uuid-char")
        var id: UUID? = null,

        @Column(nullable = false, updatable = false)
        @NotNull
        var createdAt: LocalDateTime = LocalDateTime.now().withNano(0)
) : Domain {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userAccountId", nullable = false, updatable = false)
    @NotNull
    var account: Account? = null

    @PrePersist
    fun prePersist() {
        id = id ?: UUID.randomUUID()
    }
}