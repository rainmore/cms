package com.rainmore.cms.modules.core.users.controllers

import com.rainmore.cms.models.core.users.Role
import com.rainmore.cms.modules.core.users.services.RoleRepository
import javax.inject.Inject
import org.springframework.data.domain.{Page, Pageable}
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RestController => SpringRestController}

@SpringRestController("core.users.controllers.RoleController")
@RequestMapping(Array("/api/users/roles"))
class RoleController @Inject
(
    roleRepository: RoleRepository
) {

    @GetMapping
    def list(pageable: Pageable): Page[Role] = {
        roleRepository.findAll(pageable)
    }

}
