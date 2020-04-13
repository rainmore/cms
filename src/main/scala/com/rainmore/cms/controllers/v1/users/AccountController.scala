package com.rainmore.cms.controllers.v1.users

import com.rainmore.cms.modules.core.data.Paged
import com.rainmore.cms.controllers.v1.users.dto.{AccountDto, AccountToAccountDtoConverter}
import com.rainmore.cms.modules.core.users.services.AccountRepository
import javax.inject.Inject
import org.springframework.data.domain.{Page, Pageable}
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RestController => SpringRestController}

@SpringRestController("core.users.controllers.AccountController")
@RequestMapping(Array("/api/v1/users/accounts"))
class AccountController @Inject()
(
    accountRepository: AccountRepository
) {

    @GetMapping
    def list(pageable: Pageable): Page[AccountDto] = {
        val data = accountRepository.findAll(pageable)
        Paged(data).convert(new AccountToAccountDtoConverter)
    }

}
