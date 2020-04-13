package com.rainmore.cms.modules.core.users.controllers.dto

import com.rainmore.cms.domains.core.users.Role

object RoleDto {
    def apply(role: Role): RoleDto = {
        RoleDto(role.getId, role.getName)
    }
}

case class RoleDto(id: Long, name: String)
