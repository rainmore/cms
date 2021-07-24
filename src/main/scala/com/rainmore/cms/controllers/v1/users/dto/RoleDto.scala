package com.rainmore.cms.controllers.v1.users.dto

import com.rainmore.cms.domains.users.Role

object RoleDto {
    def apply(role: Role): RoleDto = {
        RoleDto(role.getId, role.getName)
    }
}

case class RoleDto(id: Long, name: String)
