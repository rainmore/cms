package com.rainmore.cms.domains.core.users

import com.rainmore.cms.domains.Domain
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "core.users.loginToken")
@Table(name = "userLoginTokens")
data class LoginToken
(
        @Id
        @Column(name = "series", nullable = false)
        var id: String? = null,

        @Column(name = "token", nullable = false)
        var token: String? = null,

        @ManyToOne(targetEntity = Account::class)
        @JoinColumn(name = "userAccountId", nullable = false)
        var account: Account? = null,

        @Column(name = "lastUsedAt", nullable = false)
        var lastUsedAt: LocalDateTime? = null
) : Domain {

}