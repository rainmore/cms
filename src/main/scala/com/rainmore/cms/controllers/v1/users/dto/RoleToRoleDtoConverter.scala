package com.rainmore.cms.controllers.v1.users.dto

import com.rainmore.cms.domains.core.users.Role
import org.springframework.core.convert.converter.Converter

class RoleToRoleDtoConverter extends Converter[Role, RoleDto] {

    override def convert(source: Role): RoleDto = RoleDto(source)
}
