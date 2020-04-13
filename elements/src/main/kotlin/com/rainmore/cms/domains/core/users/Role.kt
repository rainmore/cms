package com.rainmore.cms.domains.core.users

import com.rainmore.cms.domains.HasId
import com.rainmore.cms.domains.Nameable
import com.rainmore.cms.domains.core.ArchivableAuditableDomain
import javax.persistence.*

@Entity(name = "core.users.role")
@Table(name = "userRoles")
data class Role
(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        override var id: Long? = null,

        @Column(nullable = false)
        override var name: String? = null,

        @Column(nullable = false)
        var isAlmighty: Boolean = false
) : ArchivableAuditableDomain<Role>(), Nameable, HasId<Long> {

    @ManyToOne
    @JoinColumn(name = "parentId")
    var parent: Role? = null

    @ManyToMany(targetEntity = Permission::class)
    @JoinTable(name = "userRolesPermissions",
            joinColumns = arrayOf(JoinColumn(name = "userRoleId")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "userPermissionId")))
    var permissions: Set<Permission> = mutableSetOf()

}