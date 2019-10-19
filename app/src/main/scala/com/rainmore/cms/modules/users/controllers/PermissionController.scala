package com.rainmore.cms.modules.users.controllers

import com.rainmore.cms.models.users.Permission
import com.rainmore.cms.modules.users.services.PermissionRepository
import javax.inject.Inject
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RestController => SpringRestController}

import scala.jdk.CollectionConverters._

@SpringRestController("users.controllers.PermissionController")
@RequestMapping(Array("/users/permissions"))
class PermissionController @Inject()
(
    permissionRepository: PermissionRepository
) {

    @GetMapping
    def list: Iterable[Permission] = {
        permissionRepository.findAll().asScala
    }

}
