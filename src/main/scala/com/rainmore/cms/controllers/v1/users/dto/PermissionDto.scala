package com.rainmore.cms.controllers.v1.users.dto

import com.rainmore.cms.domains.users.Permission

object PermissionDto {
    def apply(permission: Permission): PermissionDto = {
        PermissionDto(permission.getId, permission.getName)
    }
}

case class PermissionDto(id: Long, name: String)
