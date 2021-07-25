package com.rainmore.cms.controllers.v1.users

import com.rainmore.cms.modules.core.data.Paged
import com.rainmore.cms.controllers.v1.users.dto.{RoleDto, RoleToRoleDtoConverter}
import com.rainmore.cms.modules.core.users.services.RoleRepository
import javax.inject.Inject
import org.springframework.data.domain.{Page, Pageable}
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RestController => SpringRestController}

@SpringRestController("v1.users.RoleController")
@RequestMapping(Array("/api/v1/users/roles"))
class RoleController @Inject
(
    roleRepository: RoleRepository
) {

    @GetMapping
    def list(pageable: Pageable): Page[RoleDto] = {
        val data = roleRepository.findAll(pageable)
        Paged(data).convert(new RoleToRoleDtoConverter)
    }

}
