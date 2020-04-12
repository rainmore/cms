package com.rainmore.cms.domains.core.users

import com.rainmore.cms.domains.Domain
import com.rainmore.cms.domains.core.ArchivableAuditableDomain
import java.time.LocalDateTime
import java.util.HashSet
import javax.persistence.*

@Entity(name = "core.users.accounts")
@Table(name = "userAccounts")
data class Account
(
        @Id
        @Column(name = "id", nullable = false)
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long? = null,

        @Column(name = "firstname")
        var firstName: String? = null,

        @Column(name = "lastname")
        var lastName: String? = null,

        @Column(name = "email", nullable = false)
        var email: String? = null,

        @Column(name = "password", nullable = false)
        var password: String? = null,

        @Column(name = "status")
        @Enumerated(value = EnumType.STRING)
        var status: Status = Status.SUSPENDED

) : ArchivableAuditableDomain<Account>() {

    @Column(name = "lastLoginAt")
    var lastLoginAt: LocalDateTime? = null

    @ManyToMany(targetEntity = Role::class)
    @JoinTable(name = "userAccountsRoles",
            joinColumns = [JoinColumn(name = "userAccountId")],
            inverseJoinColumns = [JoinColumn(name = "userRoleId")])
    var roles: MutableSet<Role> = HashSet()

    @ManyToMany(targetEntity = Permission::class)
    @JoinTable(name = "userAccountsPermissions",
            joinColumns = [JoinColumn(name = "userAccountId")],
            inverseJoinColumns = [JoinColumn(name = "userPermissionId")])
    var permissions: Set<Permission> = HashSet()

    @Transient
    fun name(): String = String.format("%s %s", firstName, lastName)

}
