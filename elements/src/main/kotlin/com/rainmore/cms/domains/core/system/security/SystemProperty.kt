package com.rainmore.cms.domains.core.system.security

import com.rainmore.cms.domains.Domain
import com.rainmore.cms.domains.HasId
import com.rainmore.cms.domains.Nameable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.Transient
import javax.validation.constraints.NotNull

@Entity(name = "core.system.properties")
@Table(name = "systemProperties")
data class SystemProperty(
        @Id
        @Column(name = "name")
        override var id: String?,

        @NotNull
        @Column
        var data: String? = null
) : Domain, HasId<String>, Nameable {
    constructor(id: String) : this(id, null)

    @Transient
    override val name: String? = id
}