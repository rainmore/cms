package com.rainmore.cms.modules.users.controllers

import com.rainmore.cms.models.users.{Account, Role}
import com.rainmore.cms.modules.users.services.{AccountRepository, RoleRepository}
import javax.inject.Inject
import org.springframework.data.domain.{Page, Pageable}
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RestController => SpringRestController}

@SpringRestController("users.controllers.RoleController")
@RequestMapping(Array("/users/roles"))
class RoleController @Inject
(
    roleRepository: RoleRepository
) {

    @GetMapping
    def list(pageable: Pageable): Page[Role] = {
        roleRepository.findAll(pageable)
    }

}
