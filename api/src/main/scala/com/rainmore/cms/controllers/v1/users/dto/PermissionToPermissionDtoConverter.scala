package com.rainmore.cms.controllers.v1.users.dto

import com.rainmore.cms.domains.users.Permission
import org.springframework.core.convert.converter.Converter

class PermissionToPermissionDtoConverter extends Converter[Permission, PermissionDto] {

    override def convert(source: Permission): PermissionDto = PermissionDto(source)
}
