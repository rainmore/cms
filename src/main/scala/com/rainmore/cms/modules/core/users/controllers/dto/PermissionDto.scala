package com.rainmore.cms.modules.core.users.controllers.dto

import com.rainmore.cms.domains.core.users.Permission

object PermissionDto {
    def apply(permission: Permission): PermissionDto = {
        PermissionDto(permission.getId, permission.getName)
    }
}

case class PermissionDto(id: Long, name: String)
