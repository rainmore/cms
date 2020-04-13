package com.rainmore.cms.modules.core.users.controllers

import com.rainmore.cms.domains.core.users.Permission
import com.rainmore.cms.modules.core.users.services.PermissionRepository
import javax.inject.Inject
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RestController => SpringRestController}

import scala.jdk.CollectionConverters._

@SpringRestController("core.users.controllers.PermissionController")
@RequestMapping(Array("/api/users/permissions"))
class PermissionController @Inject()
(
    permissionRepository: PermissionRepository
) {

    @GetMapping
    def list: Iterable[Permission] = {
        permissionRepository.findAll().asScala
    }

}
