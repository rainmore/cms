package com.rainmore.cms.controllers.v1.users

import com.rainmore.cms.controllers.v1.users.dto.PermissionDto
import com.rainmore.cms.modules.core.users.services.PermissionRepository
import javax.inject.Inject
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RestController => SpringRestController}

import scala.jdk.CollectionConverters._

@SpringRestController("v1.users.PermissionController")
@RequestMapping(Array("/api/v1/users/permissions"))
class PermissionController @Inject()
(
    permissionRepository: PermissionRepository
) {

    @GetMapping
    def list: Iterable[PermissionDto] = {
        val data = permissionRepository.findAll().asScala
        data.map(PermissionDto(_))
    }

}
