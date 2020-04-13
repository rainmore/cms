package com.rainmore.cms.domains.core.users

import com.rainmore.cms.domains.Nameable
import com.rainmore.cms.domains.core.ArchivableAuditableDomain
import javax.persistence.*

@Entity(name = "core.users.permission")
@Table(name = "userPermissions")
data class Permission
(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long? = null,

        @Column(nullable = false)
        override var name: String? = null
) : ArchivableAuditableDomain<Permission>(), Nameable {

    @ManyToOne
    @JoinColumn(name = "parentId")
    var parent: Permission? = null

}
