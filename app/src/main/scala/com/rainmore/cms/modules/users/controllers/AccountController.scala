package com.rainmore.cms.modules.users.controllers

import com.rainmore.cms.models.users.Account
import com.rainmore.cms.modules.users.services.AccountRepository
import javax.inject.Inject
import org.springframework.data.domain.{Page, Pageable}
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RestController => SpringRestController}

@SpringRestController("users.controllers.AccountController")
@RequestMapping(Array("/api/users/accounts"))
class AccountController @Inject()
(
    accountRepository: AccountRepository
) {

    @GetMapping
    def list(pageable: Pageable): Page[Account] = {
        accountRepository.findAll(pageable)
    }

}
